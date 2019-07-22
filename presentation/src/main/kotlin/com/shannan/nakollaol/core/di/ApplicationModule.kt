
package com.shannan.nakollaol.core.di

import android.content.Context
import androidx.room.Room
import com.shannan.nakollaol.AndroidApplication
import com.shannan.nakollaol.BuildConfig
import com.shannan.nakollaol.data.cache.UserCacheRoomImpl
import com.shannan.nakollaol.data.cache.UserDao
import com.shannan.nakollaol.data.net.FakeBinRepositoryImpl
import com.shannan.nakollaol.data.net.KeyRepositoryImpl
import com.shannan.nakollaol.data.net.TOTPRepositoryImpl
import com.shannan.nakollaol.data.roomdb.KeyRoomDatabase
import com.shannan.nakollaol.domain.repository.BinRepository
import com.shannan.nakollaol.domain.repository.UserCache
import com.shannan.nakollaol.domain.repository.KeyRepository
import com.shannan.nakollaol.domain.repository.OTPRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    private val keyRoomDatabase: KeyRoomDatabase = Room.databaseBuilder(
            application.applicationContext,
            KeyRoomDatabase::class.java,
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
    fun providesRoomDatabase(): KeyRoomDatabase {
        return keyRoomDatabase
    }

    @Provides
    @Singleton
    fun providesKeyDao(): UserDao {
        return keyRoomDatabase.keyDao()
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
    fun provideKeyRepository(keyDataSource: KeyRepositoryImpl): KeyRepository = keyDataSource

    @Provides
    @Singleton
    fun provideBinRepository(binDataSource: FakeBinRepositoryImpl): BinRepository = binDataSource

    @Provides
    @Singleton
    fun provideOTPRepository(totpRepositoryImpl: TOTPRepositoryImpl): OTPRepository = totpRepositoryImpl

    @Provides
    @Singleton
    fun provideKeyCache(keyCache: UserCacheRoomImpl): UserCache = keyCache


}
