package kevin.weather.db

import androidx.lifecycle.LiveData
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
    fun getAll(): LiveData<List<Place>>

    @Query("SELECT * FROM place WHERE id = :id")
    fun getById(id:Int): Flow<Place>

    @Query("SELECT * FROM place WHERE name = :name AND state = :state")
    fun getByNameAndState(name:String, state:String): LiveData<Place>
}