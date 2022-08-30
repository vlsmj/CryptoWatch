package com.blueberryprojects.cryptowatch.featurecrypto.data.repositoryimpl

import com.blueberryprojects.cryptowatch.R
import com.blueberryprojects.cryptowatch.common.Constants.LIMIT_SIZE
import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.common.util.UiText
import com.blueberryprojects.cryptowatch.featurecrypto.data.datasource.CoinDao
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.CoinGeckoApi
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.toCoin
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.toCoinData
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.toCoinSearch
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApi: CoinGeckoApi,
    private val coinDao: CoinDao,
) : CoinRepository {

    override suspend fun getAllCoins() = flow {
        try {
            emit(Resource.Loading(coinDao.getAllCoins().filter {
                it.marketCapRank <= LIMIT_SIZE
            }))
            val newCoins = coinGeckoApi.getAllCoins().map {
                it.toCoin()
            }
            coinDao.deleteAllCoins()
            coinDao.insertCoins(newCoins)
            emit(Resource.Success(coinDao.getAllCoins().filter {
                it.marketCapRank <= LIMIT_SIZE
            }))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }

    override suspend fun searchCoin(query: String) = flow {
        try {
            val newCoins = coinGeckoApi.searchCoin(query).coins
            coinDao.deleteAllCoins()
            coinDao.insertCoins(newCoins.map { it.toCoinSearch() })
            emit(Resource.Success(coinDao.getAllCoins().filter {
                it.marketCapRank <= LIMIT_SIZE
            }))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }

    override suspend fun getCoinDetailsById(id: String) = flow {
        try {
            val coinDetails = coinGeckoApi.getCoinDetailsById(id)
            coinDao.deleteCoinDetails()
            coinDao.insertCoinDetails(coinDetails.toCoinData())
            emit(Resource.Success(coinDao.getCoinDetails()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}



























