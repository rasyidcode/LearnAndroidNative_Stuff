package com.rasyidcode.cryptoapptutorial.domain.model

import com.rasyidcode.cryptoapptutorial.data.remote.dto.coin_detail.Team

data class CoinDetail(
    val coinId: String,
    val name: String,
    val desc: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<Team>
)