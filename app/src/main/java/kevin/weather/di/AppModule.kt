package kevin.weather.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import kevin.weather.api.OpenWeatherMapService
import kevin.weather.db.PlaceDao
import kevin.weather.db.WeatherDb
import kevin.weather.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideOpenWeatherMapService(): OpenWeatherMapService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(OpenWeatherMapService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) : WeatherDb {
        return Room
            .databaseBuilder(app, WeatherDb::class.java, "weather.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePlaceDao(db: WeatherDb) : PlaceDao {
        return db.placeDao()
    }
}