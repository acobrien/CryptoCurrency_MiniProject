package com.example.cryptocurrency_miniproject.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cryptocurrency_miniproject.viewmodel.CryptoViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.viewmodel.CryptoUIState

@Composable
fun CryptoListScreen(
    uiState: CryptoUIState,
    onCryptoClick: (Crypto) -> Unit
) {

    LazyColumn {

        items(uiState.cryptos) { crypto ->

            CryptoItem(
                crypto = crypto,
                onCryptoClick = onCryptoClick
            )
        }
    }
}

@Composable
fun CryptoItem(
    crypto: Crypto,
    onCryptoClick: (Crypto) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onCryptoClick(crypto)
            }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = crypto.image,
                contentDescription = crypto.name,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = crypto.name)
                Text(text = crypto.symbol.uppercase())

                Text(text = "$${crypto.currentPrice}")

                Text(
                    text = "${crypto.priceChangePercentage24h}%",
                    color = if (crypto.priceChangePercentage24h >= 0)
                        Color.Green else Color.Red
                )
            }
        }
    }
}