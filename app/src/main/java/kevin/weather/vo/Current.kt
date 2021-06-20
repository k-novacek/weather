package kevin.weather.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Current(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val placeId: Int,
    val temp: Float,
    val high: Float,
    val low: Float,
    val rain: Float? = null,
    val snow: Float? = null,
    val time: Long,
    val icon: String
)
