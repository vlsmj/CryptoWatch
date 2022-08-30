package com.blueberryprojects.cryptowatch.featurecrypto.data.repositoryimpl

import com.blueberryprojects.cryptowatch.R
import com.blueberryprojects.cryptowatch.common.Constants.LIMIT_SIZE
import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.common.util.UiText
import com.blueberryprojects.cryptowatch.featurecrypto.data.datasource.CoinDao
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.*
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FakeCoinRepositoryImpl @Inject constructor(
    private val coinDao: CoinDao,
) : CoinRepository {

    private var listCoinsDto = mutableListOf<CoinDto>()
    private var coinsDto: CoinsDto
    private lateinit var coinDataDto: CoinDataDto

    init {
        (1..100).forEachIndexed { index, i ->
            listCoinsDto.add(CoinDto(
                index.toString(),
                "$i",
                "Coin $i",
                "",
                "",
                0.0,
                i,
                0.0,
                0.0,
            ))
        }

        coinsDto = CoinsDto(listCoinsDto)
    }

    override suspend fun getAllCoins() = flow {
        try {
            emit(Resource.Loading(coinDao.getAllCoins().filter {
                it.marketCapRank <= LIMIT_SIZE
            }))
            val newCoins = listCoinsDto.map {
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
            val newCoins = coinsDto.coins
            coinDao.deleteAllCoins()
            coinDao.insertCoins(newCoins.map { it.toCoinSearch() })
            emit(Resource.Success(coinDao.getAllCoins()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }

    override suspend fun getCoinById(id: String): CoinDataDto {
        return coinDataDto
    }
}