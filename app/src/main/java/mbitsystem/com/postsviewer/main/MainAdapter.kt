package mbitsystem.com.postsviewer.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_file.view.*
import mbitsystem.com.postsviewer.R
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.details.DetailsActivity
import mbitsystem.com.postsviewer.utils.KEY_INTENT_POST
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class FilesAdapter : ListAdapter<Post, FilesAdapter.FileHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileHolder(view)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) = holder.bind(getItem(position))

    inner class FileHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post) = with(view) {
            title.text = post.title
            body.text = post.body
            view.onClick {
                with(context) {
                    startActivity(intentFor<DetailsActivity>(KEY_INTENT_POST to post))
                }
            }
        }
    }

    internal fun getFileAtPosition(position: Int) = getItem(position)
}

class DiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id
                && oldItem.userId == newItem.userId
                && oldItem.title == newItem.title
                && oldItem.body == newItem.body
}