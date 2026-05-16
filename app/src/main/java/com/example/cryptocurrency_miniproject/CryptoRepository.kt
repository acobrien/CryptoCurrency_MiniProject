package com.example.cryptocurrency_miniproject

import android.util.Log
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.models.CryptoDto
import com.example.cryptocurrency_miniproject.models.toCrypto
import com.example.cryptocurrency_miniproject.remote.RetrofitInstance

class CryptoRepository {
    private val api = RetrofitInstance.api

    suspend fun getCryptos(): List<CryptoDto>? {
        return try {
            val response = api.getCoins()

            Log.d("CRYPTO_DEBUG", "SIZE: ${response.size}")
            Log.d("CRYPTO_DEBUG", "FIRST ITEM: ${response.firstOrNull()}")

            response

        } catch (e: Exception) {
            Log.e("CRYPTO_DEBUG", "ERROR: ${e.message}")
            null
        }
    }
}