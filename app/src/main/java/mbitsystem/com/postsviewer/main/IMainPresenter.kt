package mbitsystem.com.postsviewer.main

import io.reactivex.disposables.Disposable

interface IMainPresenter {
    fun bind(view: MainView)
    fun unbind()
    fun displayAllPosts(): Disposable
//    fun displayFilesDescending(): Disposable
//    fun displayFilesAscending(): Disposable
//    fun deleteFile(): Disposable
}