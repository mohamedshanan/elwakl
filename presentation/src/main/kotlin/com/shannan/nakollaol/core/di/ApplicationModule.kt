
package com.shannan.nakollaol.core.di

import android.content.Context
import androidx.room.Room
import com.shannan.nakollaol.AndroidApplication
import com.shannan.nakollaol.BuildConfig
import com.shannan.nakollaol.data.cache.UserCacheRoomImpl
import com.shannan.nakollaol.data.cache.UserDao
import com.shannan.nakollaol.data.net.restaurants.RestaurantsRepositoryImpl
import com.shannan.nakollaol.data.net.users.UserRepositoryImpl
import com.shannan.nakollaol.data.roomdb.ElwaklRoomDatabase
import com.shannan.nakollaol.domain.repository.RestaurantsRepository
import com.shannan.nakollaol.domain.repository.UserCache
import com.shannan.nakollaol.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    private val elwaklRoomDatabase: ElwaklRoomDatabase = Room.databaseBuilder(
            application.applicationContext,
            ElwaklRoomDatabase::class.java,
            "Key_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://e-shannan.com/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(): ElwaklRoomDatabase {
        return elwaklRoomDatabase
    }

    @Provides
    @Singleton
    fun providesKeyDao(): UserDao {
        return elwaklRoomDatabase.userDao()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providesUserRepository(keyDataSource: UserRepositoryImpl): UserRepository = keyDataSource

    @Provides
    @Singleton
    fun providesRestaurantsRepository(restaurantsRepo: RestaurantsRepositoryImpl): RestaurantsRepository = restaurantsRepo

    @Provides
    @Singleton
    fun providesUserCache(cache: UserCacheRoomImpl): UserCache = cache
}
