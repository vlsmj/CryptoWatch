package com.blueberryprojects.cryptowatch.di

import android.app.Application
import androidx.room.Room
import com.blueberryprojects.cryptowatch.featurecrypto.data.datasource.CryptoDatabase
import com.blueberryprojects.cryptowatch.featurecrypto.data.repositoryimpl.FakeCoinRepositoryImpl
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins.CoinsUseCases
import com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins.GetAllCoinsUseCase
import com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins.SearchCoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleTest {

    @Provides
    @Singleton
    fun provideCryptoDatabase(app: Application): CryptoDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            CryptoDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinRepositoryImpl(database: CryptoDatabase): CoinRepository {
        return FakeCoinRepositoryImpl(database.coinDao())
    }

    @Provides
    @Singleton
    fun provideCoinsUseCases(coinRepository: CoinRepository) = CoinsUseCases(
        getAllCoinsUseCase = GetAllCoinsUseCase(coinRepository),
        searchCoinUseCase = SearchCoinUseCase(coinRepository)
    )
}