package mbitsystem.com.postsviewer.injection

import dagger.Module
import dagger.Provides
import mbitsystem.com.postsviewer.InitApp
import mbitsystem.com.postsviewer.data.PostInteractor
import mbitsystem.com.postsviewer.details.DetailsPresenter
import mbitsystem.com.postsviewer.details.IDetailsPresenter
import mbitsystem.com.postsviewer.main.IMainPresenter
import mbitsystem.com.postsviewer.main.MainPresenter
import mbitsystem.com.postsviewer.utils.AppSchedulerProvider
import mbitsystem.com.postsviewer.utils.SchedulerProvider
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
    fun providesMainPresenter(postInteractor: PostInteractor, schedulerProvider: AppSchedulerProvider): IMainPresenter =
        MainPresenter(postInteractor, schedulerProvider)

    @Singleton
    @Provides
    fun providesDetailsPresenter(postInteractor: PostInteractor,schedulerProvider: AppSchedulerProvider): IDetailsPresenter =
        DetailsPresenter(postInteractor, schedulerProvider)

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}