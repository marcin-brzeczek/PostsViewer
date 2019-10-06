package mbitsystem.com.postsviewer.data.network

import io.reactivex.Observable
import mbitsystem.com.postsviewer.data.model.Post
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}