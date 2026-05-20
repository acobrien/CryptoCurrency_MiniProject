package com.example.cryptocurrency_miniproject.viewmodel

import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.models.SearchCoin

data class CryptoUIState(
    val cryptos: List<Crypto> = emptyList(),
    val selectedCrypto: Crypto? = null,
    val searchResults: List<Crypto> = emptyList()
)
