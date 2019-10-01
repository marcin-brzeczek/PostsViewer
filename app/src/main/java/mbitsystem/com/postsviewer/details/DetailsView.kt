package mbitsystem.com.postsviewer.details

import io.reactivex.Observable
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.state.PostState

interface DetailsView {
  fun render(state: PostState)
  fun displayPostIntent(): Observable<Post>
}