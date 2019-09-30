package mbitsystem.com.postsviewer.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Post(
    @JsonProperty("userId")
    val userId: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("body")
    val body: String
) : PaperParcelable {
    override fun toString() = title

    companion object {
        @JvmField
        val CREATOR = PaperParcelPost.CREATOR
    }
}