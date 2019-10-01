package mbitsystem.com.postsviewer

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector
import mbitsystem.com.postsviewer.injection.ApiModule
import mbitsystem.com.postsviewer.injection.AppModule
import mbitsystem.com.postsviewer.injection.DaggerAppComponent
import timber.log.Timber

const val apiUrl: String = "https://jsonplaceholder.typicode.com"

class InitApp : DaggerApplication(), HasActivityInjector {
    private val _applicationInjector by lazy {
        DaggerAppComponent.builder().let {
            it.seedInstance(this)
            it.setAppModule(AppModule(this))
            it.setApiModule(ApiModule( apiUrl))
            it.build()
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = _applicationInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
