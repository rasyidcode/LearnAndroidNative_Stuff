package com.rasyidcode.cryptoapptutorial.presentation.coin_detail

import com.rasyidcode.cryptoapptutorial.domain.model.Coin
import com.rasyidcode.cryptoapptutorial.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)