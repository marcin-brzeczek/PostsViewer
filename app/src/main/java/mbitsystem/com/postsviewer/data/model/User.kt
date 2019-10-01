package mbitsystem.com.postsviewer.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class User(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String
    ) : PaperParcelable {
    override fun toString() = name

    companion object {
        @JvmField
        val CREATOR = PaperParcelPost.CREATOR
    }
}