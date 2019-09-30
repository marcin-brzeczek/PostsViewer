package mbitsystem.com.postsviewer.details

import io.reactivex.disposables.Disposable

interface IDetailsPresenter {
    fun bind(view: DetailsView)
    fun unbind()
//    fun observeFileDisplayIntent(): Disposable
}