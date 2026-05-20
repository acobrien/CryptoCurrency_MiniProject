package com.example.cryptocurrency_miniproject.models


data class Crypto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double
) {
    fun getCoinGeckoUrl(): String =
        "https://www.coingecko.com/en/coins/$id"

    fun isPositiveChange(): Boolean =
            priceChangePercentage24h >= 0
}