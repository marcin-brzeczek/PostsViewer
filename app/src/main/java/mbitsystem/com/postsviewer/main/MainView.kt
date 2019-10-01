package mbitsystem.com.postsviewer.main

import io.reactivex.Observable
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.state.PostState

interface MainView {
    fun render(state: PostState)
    fun getFilesIntent(): Observable<Unit>
}
