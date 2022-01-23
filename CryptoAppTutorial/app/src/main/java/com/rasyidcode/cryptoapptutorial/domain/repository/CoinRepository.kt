package com.rasyidcode.cryptoapptutorial.domain.repository

import com.rasyidcode.cryptoapptutorial.data.remote.dto.CoinDetailDto
import com.rasyidcode.cryptoapptutorial.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}