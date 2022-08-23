package com.blueberryprojects.cryptowatch.di

import com.blueberryprojects.cryptowatch.featurecrypto.data.repositoryimpl.CoinRepositoryImpl
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinRepositoryImpl(
        coinRepositoryImpl: CoinRepositoryImpl,
    ): CoinRepository
}