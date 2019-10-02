package mbitsystem.com.postsviewer.data

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import mbitsystem.com.postsviewer.data.model.Comment
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.model.PostDetails
import mbitsystem.com.postsviewer.data.model.User
import mbitsystem.com.postsviewer.data.network.CommentsApi
import mbitsystem.com.postsviewer.data.network.PostsApi
import mbitsystem.com.postsviewer.data.network.UsersApi
import mbitsystem.com.postsviewer.state.PostState
import javax.inject.Inject

class PostInteractor @Inject constructor(
    val postApi: PostsApi,
    val usersApi: UsersApi,
    val commentApi: CommentsApi
) : Interactor {

    override fun getPosts(): Observable<PostState> = postApi.getPosts().toObservable()
        .map<PostState> { PostState.DataState(it) }
        .onErrorReturn { PostState.ErrorState(it.message) }

    fun getPostDetails(post: Post): Observable<PostState> {
        return composePostDetails(post, usersApi, commentApi).toObservable()
            .map<PostState> { PostState.LoadPostDetails(it) }
            .onErrorReturn { PostState.ErrorState(it.message) }
    }

    private fun composePostDetails(
        post: Post,
        usersApi: UsersApi,
        commentApi: CommentsApi
    ): Single<PostDetails> {
        return Single.zip(
            usersApi.getUsersById(post.userId),
            commentApi.getCommentsByPostId(post.id),
            BiFunction<List<User>, List<Comment>, PostDetails> { user, comments ->
                createDetailsPostModel(post, user, comments)
            }
        )
    }

    private fun createDetailsPostModel(
        post: Post,
        users: List<User>,
        comments: List<Comment>
    ): PostDetails {
        return PostDetails(post.title, post.body, users.first().name, users.first().email, comments)
    }
}