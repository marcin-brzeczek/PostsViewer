package mbitsystem.com.postsviewer.state

import mbitsystem.com.postsviewer.data.model.Post

sealed class PostState {
    object LoadingState : PostState()
    data class DataState(val data: List<Post>) : PostState()
    data class LoadPostDetails(val postDetails: Post) : PostState()
    data class ErrorState(val error: String?) : PostState()
}