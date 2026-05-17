package com.example.cryptocurrency_miniproject.viewmodel

import com.example.cryptocurrency_miniproject.models.Crypto

data class CryptoUIState(
    val cryptos: List<Crypto> = emptyList(),
    val selectedCrypto: Crypto? = null
)
