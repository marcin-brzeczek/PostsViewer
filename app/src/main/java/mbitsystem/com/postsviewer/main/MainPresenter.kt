package mbitsystem.com.postsviewer.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.state.PostState
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(val postInteractor: PostInteractor) : IMainPresenter {

    lateinit var view: MainView
    private val compositeDisposable = CompositeDisposable()

    override fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(displayAllPosts())
//        compositeDisposable.add(displayFilesAscending())
//        compositeDisposable.add(displayFilesDescending())
//        compositeDisposable.add(deleteFile())
    }

    override fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun displayAllPosts() = view.getFilesIntent()
        .doOnNext { Timber.d("Intent: Display Files" ) }
        .flatMap<PostState> { postInteractor.getPosts() }
        .startWith(PostState.LoadingState)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }


//    override fun displayFilesDescending() = view.getFilesDescendingIntent()
//        .doOnNext { Timber.d("Intent: Display Files Desceding") }
//        .flatMap<PostState> { postInteractor.getFilesDesceding() }
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe { view.render(it) }
//
//
//    override fun displayFilesAscending() = view.getFilesAscendingIntent()
//        .doOnNext { Timber.d("Intent: Display Files Asceding") }
//        .flatMap<PostState> { postInteractor.getFilesAsceding() }
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe { view.render(it) }
//
//    override fun deleteFile() = view.deleteMovieIntent()
//        .doOnNext { Timber.d("Intent: Delete file") }
//        .subscribeOn(AndroidSchedulers.mainThread())
//        .observeOn(Schedulers.io())
//        .flatMap<Unit> { postInteractor.deleteFile(it) }
//        .subscribe()
}