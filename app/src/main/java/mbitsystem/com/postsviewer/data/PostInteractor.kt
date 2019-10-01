package mbitsystem.com.postsviewer.data

import io.reactivex.Observable
import mbitsystem.com.postsviewer.data.network.PostsApi
import mbitsystem.com.postsviewer.state.PostState
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.inject.Inject

class PostInteractor @Inject constructor(val postApi: PostsApi) : Interactor {

    override fun getPosts(): Observable<PostState> = postApi.getPosts().toObservable()
        .map<PostState> { PostState.DataState(it) }
        .onErrorReturn { PostState.ErrorState(it.message) }

    fun getPostDetails(postId: String): Observable<PostState> {
        return  postApi.getPostDetailsById(postId).toObservable()
            .map<PostState> { PostState.LoadPostDetails(it) }
            .onErrorReturn { PostState.ErrorState(it.message) }
    }

    private fun getInputStreamFromUrlString(urlString: String): InputStream {
        return try {
            val inputStream = URL(urlString).openConnection().getInputStream()
            BufferedInputStream(inputStream)
        } catch (e: IOException) {
            throw IOException(e.message)
        }
    }
}