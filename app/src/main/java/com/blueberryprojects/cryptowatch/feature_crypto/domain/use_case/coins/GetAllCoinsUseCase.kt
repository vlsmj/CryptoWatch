package com.blueberryprojects.cryptowatch.feature_crypto.domain.use_case.coins

import com.blueberryprojects.cryptowatch.R
import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.common.util.UiText
import com.blueberryprojects.cryptowatch.feature_crypto.data.data_source.CoinDao
import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto.toCoin
import com.blueberryprojects.cryptowatch.feature_crypto.domain.model.Coin
import com.blueberryprojects.cryptowatch.feature_crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val coinDao: CoinDao,
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading(coinDao.getAllCoins()))
        try {
            val newCoins = coinRepository.getAllCoins()
            coinDao.deleteAllCoins()
            coinDao.insertCoins(newCoins.map { it.toCoin() })
            emit(Resource.Success(coinDao.getAllCoins()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}