package mbitsystem.com.postsviewer.main

import io.reactivex.disposables.CompositeDisposable
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.state.PostState
import mbitsystem.com.postsviewer.utils.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(
     val postInteractor: PostInteractor,
     val schedulerProvider: SchedulerProvider
    ) : IMainPresenter {

    lateinit var view: MainView
    private val compositeDisposable = CompositeDisposable()

    override fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(displayAllPosts())
    }

    override fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun displayAllPosts() = view.getFilesIntent()
        .doOnNext { Timber.d("Intent: Display Posts" ) }
        .flatMap<PostState> { postInteractor.getPosts() }
        .startWith(PostState.LoadingState)
        .subscribeOn(schedulerProvider.ioScheduler())
        .observeOn(schedulerProvider.uiScheduler())
        .subscribe { view.render(it) }
}