package mbitsystem.com.postsviewer.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mbitsystem.com.postsviewer.details.DetailsActivity
import mbitsystem.com.postsviewer.main.MainActivity

@Module
abstract class ActivityInjectorsModule {
    @ContributesAndroidInjector abstract fun provideMainActivityInjector(): MainActivity
    @ContributesAndroidInjector abstract fun provideDetailsActivityInjector(): DetailsActivity
}