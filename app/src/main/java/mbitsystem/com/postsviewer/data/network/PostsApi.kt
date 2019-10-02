package mbitsystem.com.postsviewer.data.network

import io.reactivex.Single
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>

    @GET("/posts")
    fun getPostDetailsById(@Query("id") number: String): Single<Post>

//    tytuł, treść (pełną), imię i nazwisko autora, listę komentarzy.
}