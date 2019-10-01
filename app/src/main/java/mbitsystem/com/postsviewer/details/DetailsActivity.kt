package mbitsystem.com.postsviewer.details

import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_details.titlePost
import kotlinx.android.synthetic.main.activity_details.progress_bar
import kotlinx.android.synthetic.main.activity_details.body
import mbitsystem.com.postsviewer.R
import mbitsystem.com.postsviewer.base.BaseActivity
import mbitsystem.com.postsviewer.data.model.Post
import mbitsystem.com.postsviewer.main.MainActivity
import mbitsystem.com.postsviewer.state.PostState
import mbitsystem.com.postsviewer.utils.KEY_INTENT_POST
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsView {

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
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

    private fun renderLoadPost(postDetails: Post) {
        progress_bar.visibility = View.GONE
        setLayoutData(postDetails)
    }

    private fun setLayoutData(detailsPost: Post) {
        titlePost.text = detailsPost.title
        body.text = detailsPost.body
    }

    private fun renderErrorState(error: String?) {
        progress_bar.visibility = View.GONE
        error?.let { longToast(getString(R.string.error_loading_file) + it) }
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }

    override fun onBackPressed() {
        startActivity(intentFor<MainActivity>().newTask().clearTask())
    }
}