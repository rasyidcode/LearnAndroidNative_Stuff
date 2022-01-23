package com.rasyidcode.cryptoapptutorial.domain.use_case.get_coin

import android.util.Log
import com.rasyidcode.cryptoapptutorial.common.Resource
import com.rasyidcode.cryptoapptutorial.data.remote.dto.toCoin
import com.rasyidcode.cryptoapptutorial.data.remote.dto.toCoinDetail
import com.rasyidcode.cryptoapptutorial.domain.model.Coin
import com.rasyidcode.cryptoapptutorial.domain.model.CoinDetail
import com.rasyidcode.cryptoapptutorial.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
//            Log.d("GetCoinUseCase", "$coin")
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

}