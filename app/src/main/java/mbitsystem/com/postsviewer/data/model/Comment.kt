package mbitsystem.com.postsviewer.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Comment(
    @JsonProperty("postId")
    val postId: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("body")
    val body: String
) : PaperParcelable {
    override fun toString() = name

    companion object {
        @JvmField
        val CREATOR = PaperParcelComment.CREATOR
    }
}