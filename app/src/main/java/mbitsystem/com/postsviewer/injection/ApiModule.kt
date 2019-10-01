package mbitsystem.com.postsviewer.injection

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import mbitsystem.com.postsviewer.InitApp
import mbitsystem.com.postsviewer.data.network.PostsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerModule(KotlinModule())))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Provides
    fun providePostsApi(retrofit: Retrofit) = retrofit.create(PostsApi::class.java)
}