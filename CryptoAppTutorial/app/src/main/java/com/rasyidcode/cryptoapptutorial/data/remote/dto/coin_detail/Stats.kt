package com.rasyidcode.cryptoapptutorial.data.remote.dto.coin_detail


import com.google.gson.annotations.SerializedName

data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)