package mbitsystem.com.postsviewer.data.network

import io.reactivex.Single
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("/users")
    fun getUsersById(@Query("id") id: String): Single<List<User>>
}