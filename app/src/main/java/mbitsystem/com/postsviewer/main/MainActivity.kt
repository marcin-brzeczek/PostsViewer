package mbitsystem.com.postsviewer.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import mbitsystem.com.postsviewer.R
import mbitsystem.com.postsviewer.base.BaseActivity
import mbitsystem.com.postsviewer.state.PostState
import org.jetbrains.anko.longToast
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun render(state: PostState) {
        Timber.d("State: ${state.javaClass.simpleName}")
        when (state) {
            is PostState.LoadingState -> renderLoadingState()
            is PostState.DataState -> renderDataState(state)
            is PostState.ErrorState -> renderErrorState(state.error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = MainAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        presenter.bind(this)
    }

    override fun getFilesIntent(): Observable<Unit> = Observable.just(Unit)

    private fun renderLoadingState() {
        recycler_view.isEnabled = false
        progress_bar.visibility = View.VISIBLE
    }

    private fun renderDataState(dataState: PostState.DataState) {
        progress_bar.visibility = View.GONE
        recycler_view.apply {
            isEnabled = true
            (adapter as MainAdapter).submitList(dataState.data)
        }
    }

    private fun renderErrorState(error: String?) = error?.let {
        longToast(getString(R.string.error_load_data) + it)
    }


    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }
}