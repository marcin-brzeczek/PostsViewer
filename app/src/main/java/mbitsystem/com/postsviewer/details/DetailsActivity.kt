package mbitsystem.com.postsviewer.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_details.*
import mbitsystem.com.postsviewer.R
import mbitsystem.com.postsviewer.base.BaseActivity
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.data.model.PostDetails
import mbitsystem.com.postsviewer.main.MainActivity
import mbitsystem.com.postsviewer.state.PostState
import mbitsystem.com.postsviewer.utils.KEY_INTENT_POST
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask
import timber.log.Timber
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsView {

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        recycler_view.adapter = DetailsAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)

        author.setOnClickListener {sendEmail(presenter.userEmail, "Application PostViewer") }
        presenter.bind(this)
    }

    override fun render(state: PostState) {
        when (state) {
            is PostState.LoadingState -> renderLoadingState()
            is PostState.LoadPostDetails -> renderLoadPost(state.postDetails)
            is PostState.ErrorState -> renderErrorState(state.error)
        }
    }

    override fun displayPostIntent(): Observable<Post> =
        Observable.just(intent.extras.getParcelable(KEY_INTENT_POST) as Post)

    private fun renderLoadingState() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun renderLoadPost(postDetails: PostDetails) {
        progress_bar.visibility = View.GONE
        setLayoutData(postDetails)
    }

    private fun setLayoutData(detailsPost: PostDetails) {
        presenter.userEmail = detailsPost.userEmail
        authorLabel.visibility = View.VISIBLE
        comments.visibility = View.VISIBLE
        titlePost.text = detailsPost.title
        body.text = detailsPost.body
        author.text = detailsPost.userName
        recycler_view.apply {
            isEnabled = true
            (adapter as DetailsAdapter).submitList(detailsPost.comments)
        }
    }

    private fun renderErrorState(error: String?) {
        progress_bar.visibility = View.GONE
        error?.let { longToast(getString(R.string.error_loading_post) + it)
        Timber.d("Error loading post details: \n $it")
        }
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }

    private fun sendEmail(recipient: String, subject: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            longToast("Error sending email")
        }
    }


    override fun onBackPressed() {
        startActivity(intentFor<MainActivity>().newTask().clearTask())
    }
}