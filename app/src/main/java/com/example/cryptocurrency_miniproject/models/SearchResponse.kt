package com.example.cryptocurrency_miniproject.models

data class SearchResponse(
    val coins: List<SearchCoin>
)
data class SearchCoin(
    val id: String,
    val name: String,
    val symbol: String,
    val thumb: String
)
fun SearchCoin.toCrypto(): Crypto {

    return Crypto(
        id = id,
        name = name,
        symbol = symbol,
        image = thumb,
        currentPrice = 0.0,
        priceChangePercentage24h = 0.0
    )
}