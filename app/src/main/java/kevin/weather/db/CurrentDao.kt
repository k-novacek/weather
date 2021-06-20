package kevin.weather.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kevin.weather.vo.Current

@Dao
interface CurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(current: Current)

    @Query("SELECT * FROM current WHERE placeId = :placeId")
    fun findByPlaceId(placeId: Int) : LiveData<Current>
}