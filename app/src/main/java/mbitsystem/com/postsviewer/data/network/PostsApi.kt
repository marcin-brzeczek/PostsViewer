package mbitsystem.com.postsviewer.data.network

import io.reactivex.Single
import mbitsystem.com.postsviewer.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

//    @GET("kontakty/lista")
//    fun getPosts(): Deferred<Response<ContactsRequest>>


//    @GET("kontakty?tel={phoneNumber}")
//    fun getContactByPhoneNumber(
//        @retrofit2.http.Path("phoneNumber") nummber:String
//    ): Deferred<Response<ShortContactModel>>

    @GET("/users/1/posts")
    fun getPosts(): Single<List<Post>>

    @GET("kontakt")
    fun getContactByPhoneNumber(
        @Query("tel")  number:String)
            : Single<Response<Post>>
}