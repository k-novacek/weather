package kevin.weather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import kevin.weather.repository.PlaceRepository
import kevin.weather.util.AbsentLiveData
import kevin.weather.vo.Place
import kevin.weather.vo.Resource
import javax.inject.Inject

class PlaceViewModel @Inject constructor(placeRepository: PlaceRepository) : ViewModel() {
    private val _placeId: MutableLiveData<PlaceId> = MutableLiveData()
    val placeId: LiveData<PlaceId>
        get() = _placeId
    val place: LiveData<Resource<Place>> = _placeId.switchMap { input ->
        input.ifExists { name, state ->
            placeRepository.loadPlace(name, state)
        }
    }

    fun retry() {
        val name = _placeId.value?.name
        val state = _placeId.value?.state
        if (name != null && state != null) {
            _placeId.value = PlaceId(name, state)
        }
    }

    fun setId(name: String, state: String) {
        val update = PlaceId(name, state)
        if (_placeId.value == update) {
            return
        }
        _placeId.value = update
    }

    data class PlaceId(val name: String, val state: String) {
        fun <T> ifExists(f: (String, String) -> LiveData<T>): LiveData<T> {
            return if (name.isBlank() || state.isBlank()) {
                AbsentLiveData.create()
            } else {
                f(name, state)
            }
        }
    }
}