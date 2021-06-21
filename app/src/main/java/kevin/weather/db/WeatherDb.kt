package kevin.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kevin.weather.vo.Place

@Database(
    entities = [
        Place::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDb : RoomDatabase() {
    abstract  fun placeDao(): PlaceDao
}