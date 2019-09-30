package mbitsystem.com.postsviewer.injection

import dagger.Module
import dagger.Provides
import mbitsystem.com.postsviewer.InitApp
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.details.DetailsPresenter
import mbitsystem.com.postsviewer.details.IDetailsPresenter
import mbitsystem.com.postsviewer.main.IMainPresenter
import mbitsystem.com.postsviewer.main.MainPresenter
import javax.inject.Singleton

@Module
class AppModule(val application: InitApp) {

    @Provides
    @Singleton
    fun providesApplication(): InitApp {
        return application
    }

      @Singleton
    @Provides
    fun providesMainPresenter(postInteractor: PostInteractor): IMainPresenter = MainPresenter(postInteractor)

    @Singleton
    @Provides
    fun providesDetailsPresenter(postInteractor: PostInteractor): IDetailsPresenter = DetailsPresenter(postInteractor)
}