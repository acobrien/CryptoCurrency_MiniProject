package com.example.cryptocurrency_miniproject

import android.util.Log
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.models.CryptoDto
import com.example.cryptocurrency_miniproject.models.SearchCoin
import com.example.cryptocurrency_miniproject.models.toCrypto
import com.example.cryptocurrency_miniproject.remote.RetrofitInstance

class CryptoRepository {
    private val api = RetrofitInstance.api
    suspend fun getCryptos(): List<CryptoDto>? {
        return try {
            api.getCoins()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun searchCoins(query: String): List<SearchCoin>? {
        return try {
            api.searchCoins(query).coins
        } catch (e: Exception) {
            null
        }
    }
}