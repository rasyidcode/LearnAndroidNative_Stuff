package com.rasyidcode.cryptoapptutorial.data.repository

import com.rasyidcode.cryptoapptutorial.data.remote.CoinPaprikaApi
import com.rasyidcode.cryptoapptutorial.data.remote.dto.CoinDetailDto
import com.rasyidcode.cryptoapptutorial.data.remote.dto.CoinDto
import com.rasyidcode.cryptoapptutorial.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}