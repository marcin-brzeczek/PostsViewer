package mbitsystem.com.postsviewer.details

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.state.PostState
import mbitsystem.com.postsviewer.utils.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
     val postInteractor: PostInteractor,
     val schedulerProvider: SchedulerProvider
) : IDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: DetailsView

    internal var userEmail = ""

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
        .doOnNext { Timber.d("Intent: Display Post ${it.title}") }
        .flatMap<PostState> { postInteractor.getPostDetails(it) }
        .subscribeOn(schedulerProvider.ioScheduler())
        .observeOn(schedulerProvider.uiScheduler())
        .doOnSubscribe { view.render(PostState.LoadingState) }
        .subscribe { view.render(it) }
}