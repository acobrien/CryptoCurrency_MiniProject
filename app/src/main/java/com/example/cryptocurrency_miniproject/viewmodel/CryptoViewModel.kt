package com.example.cryptocurrency_miniproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency_miniproject.CryptoRepository
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.models.toCrypto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val repository = CryptoRepository()

    private val _cryptos = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptos = _cryptos.asStateFlow()

    init {
        loadCryptos()
    }

    private fun loadCryptos() {
        viewModelScope.launch {

            val result = repository.getCryptos()

            if (result != null) {
                _cryptos.value = result.map { it.toCrypto() }
            }
        }
    }
}