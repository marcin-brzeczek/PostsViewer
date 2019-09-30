package mbitsystem.com.postsviewer.state

import mbitsystem.com.postsviewer.data.model.Post
import java.io.InputStream

sealed class PostState {
    object LoadingState : PostState()
    data class DataState(val data: List<Post>) : PostState()
    data class LoadPostFromStream(val stream: InputStream) : PostState()
    data class ErrorState(val error: String?) : PostState()
}