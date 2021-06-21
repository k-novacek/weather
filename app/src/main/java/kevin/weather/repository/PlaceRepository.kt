package kevin.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kevin.weather.AppExecutors
import kevin.weather.api.ApiEmptyResponse
import kevin.weather.api.ApiResponse
import kevin.weather.db.PlaceDao
import kevin.weather.db.WeatherDb
import kevin.weather.util.RateLimiter
import kevin.weather.vo.Place
import kevin.weather.vo.Resource
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: WeatherDb,
    private val placeDao: PlaceDao
) {

    fun loadPlace(id: Int): Flow<Place> {
        return placeDao.getById(id)
    }

    fun loadPlace(name: String, state: String): LiveData<Resource<Place>> {
        return object : NetworkBoundResource<Place, Place>(appExecutors) {
            override fun saveCallResult(item: Place) {
            }

            override fun shouldFetch(data: Place?): Boolean {
                return false
            }

            override fun loadFromDb() = placeDao.getByNameAndState(name, state)

            override fun createCall(): LiveData<ApiResponse<Place>> {
                return MutableLiveData<ApiResponse<Place>>(ApiEmptyResponse<Place>())
            }
        }.asLiveData()
    }

    fun loadPlaces() : LiveData<Resource<List<Place>>> {
        return object : NetworkBoundResource<List<Place>, List<Place>>(appExecutors) {
            override fun saveCallResult(item: List<Place>) {
            }

            override fun shouldFetch(data: List<Place>?): Boolean {
                return false
            }

            override fun loadFromDb() = placeDao.getAll()

            override fun createCall(): LiveData<ApiResponse<List<Place>>> {
                return MutableLiveData<ApiResponse<List<Place>>>(ApiEmptyResponse<List<Place>>())
            }
        }.asLiveData()
    }
}