package com.blueberryprojects.cryptowatch.di

import android.app.Application
import androidx.room.Room
import com.blueberryprojects.cryptowatch.common.Constants.BASE_URL
import com.blueberryprojects.cryptowatch.feature_crypto.data.data_source.CryptoDatabase
import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.CoinGeckoApi
import com.blueberryprojects.cryptowatch.feature_crypto.domain.repository.CoinRepository
import com.blueberryprojects.cryptowatch.feature_crypto.domain.use_case.coins.CoinsUseCases
import com.blueberryprojects.cryptowatch.feature_crypto.domain.use_case.coins.GetAllCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoDatabase(app: Application): CryptoDatabase {
        return Room.databaseBuilder(
            app,
            CryptoDatabase::class.java, "db_crypto"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): CoinGeckoApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinsUseCases(coinRepository: CoinRepository, database: CryptoDatabase) = CoinsUseCases(
        getAllCoinsUseCase = GetAllCoinsUseCase(coinRepository, database.coinDao())
    )
}

















