package com.example.cryptocurrency_miniproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency_miniproject.CryptoRepository
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.models.toCrypto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository()

    private val _uiState = MutableStateFlow(CryptoUIState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCryptos()
    }

    private fun loadCryptos() {

        viewModelScope.launch {

            val result = repository.getCryptos()

            if (result != null) {

                _uiState.update { state ->

                    state.copy(
                        cryptos = result.map { dto ->
                            dto.toCrypto()
                        }
                    )
                }
            }
        }
    }

    fun setSelectedCrypto(crypto: Crypto) {

        _uiState.update { state ->

            state.copy(
                selectedCrypto = crypto
            )
        }
    }

    fun searchCoins(query: String) {

        viewModelScope.launch {

            val result = repository.searchCoins(query)

            result?.let { searchList ->

                _uiState.update { state ->

                    state.copy(
                        searchResults = searchList.map {
                            it.toCrypto()
                        }
                    )
                }
            }
        }
    }
}