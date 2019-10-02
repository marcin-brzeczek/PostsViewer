package mbitsystem.com.postsviewer.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_item.view.*
import mbitsystem.com.postsviewer.R
import mbitsystem.com.postsviewer.data.model.Comment

class DetailsAdapter : ListAdapter<Comment, DetailsAdapter.CommentHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) = holder.bind(getItem(position))

    inner class CommentHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comment: Comment) = with(view) {
            title.text = comment.name
            email.text = comment.email
            body.text = comment.body
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
        oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.email == newItem.email
                && oldItem.body == newItem.body
}