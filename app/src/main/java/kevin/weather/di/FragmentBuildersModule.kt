package kevin.weather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kevin.weather.ui.place.PlaceFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePlaceFragment(): PlaceFragment
}