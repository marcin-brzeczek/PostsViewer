package mbitsystem.com.postsviewer.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.state.PostState
import timber.log.Timber
import javax.inject.Inject

class DetailsPresenter @Inject constructor(val postInteractor: PostInteractor) : IDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: DetailsView

    override fun bind(view: DetailsView) {
        this.view = view
        compositeDisposable.add(observePostDisplayIntent())
    }

    override fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun observePostDisplayIntent(): Disposable = view.displayPostIntent()
        .doOnNext { Timber.d("Intent: Display Post") }
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.render(PostState.LoadingState) }
        .subscribe { view.render(PostState.LoadPostDetails(it)) }
}