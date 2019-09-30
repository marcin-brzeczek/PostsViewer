package mbitsystem.com.postsviewer.data

import io.reactivex.Observable
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.state.PostState

interface Interactor {
    fun getPosts(): Observable<PostState>
//    fun getFilesAsceding(): Observable<PostState>
//    fun getFilesDesceding(): Observable<PostState>
//    fun deleteFile(post: Post): Observable<Unit>
}