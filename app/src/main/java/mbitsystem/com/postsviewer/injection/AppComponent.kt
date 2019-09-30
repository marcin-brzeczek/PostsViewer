package mbitsystem.com.postsviewer.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import mbitsystem.com.postsviewer.InitApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityInjectorsModule::class,
        AppModule::class,
        ApiModule::class]
)
interface AppComponent : AndroidInjector<InitApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<InitApp>() {
        abstract fun setAppModule(module: AppModule)
        abstract fun setApiModule(module: ApiModule)
        abstract override fun build(): AppComponent
    }
}