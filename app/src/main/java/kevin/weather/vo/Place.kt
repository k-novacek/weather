package kevin.weather.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val state : String,
    val lat: Double,
    val lon: Double
)
