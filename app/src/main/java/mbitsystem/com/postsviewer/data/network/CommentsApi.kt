package mbitsystem.com.postsviewer.data.network

import io.reactivex.Single
import mbitsystem.com.postsviewer.data.model.Comment
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsApi {

    @GET("/comments")
    fun getCommentsByPostId(@Query("postId") postId: String): Single<List<Comment>>
}