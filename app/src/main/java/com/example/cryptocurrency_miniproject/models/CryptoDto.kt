package com.example.cryptocurrency_miniproject.models

data class CryptoDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val price_change_percentage_24h: Double?
)

fun CryptoDto.toCrypto(): Crypto {
    return Crypto(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        currentPrice = current_price,
        priceChangePercentage24h = price_change_percentage_24h ?: 0.0
    )
}
