package mbitsystem.com.postsviewer.data.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class PostDetails(
    val title: String,
    val body: String,
    val userName: String,
    val comments: List<Comment>
) : PaperParcelable {
    override fun toString() = title

    companion object {
        @JvmField
        val CREATOR = PaperParcelPostDetails.CREATOR
    }
}