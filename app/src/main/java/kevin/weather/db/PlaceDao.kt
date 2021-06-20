package kevin.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kevin.weather.vo.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(place: Place)

    @Query("SELECT * FROM place")
    fun getAll(): Flow<List<Place>>

    @Query("SELECT * FROM place WHERE id = :id")
    fun getById(id:Int): Flow<Place>
}