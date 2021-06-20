package kevin.weather.repository

import kevin.weather.db.PlaceDao
import kevin.weather.vo.Place
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val placeDao: PlaceDao
) {

    fun loadPlace(id: Int): Flow<Place> {
        return placeDao.getById(id)
    }

    fun getAllPlaces(): Flow<List<Place>> {
        return placeDao.getAll()
    }
}