package kevin.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kevin.weather.ui.place.PlaceViewModel
import kevin.weather.viewmodel.WeatherViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlaceViewModel::class)
    abstract fun bindPlaceViewModel(placeViewModel: PlaceViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WeatherViewModelFactory) : ViewModelProvider.Factory
}