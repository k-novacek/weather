package kevin.weather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kevin.weather.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}