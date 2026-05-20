package com.example.cryptocurrency_miniproject.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptocurrency_miniproject.models.Crypto

@Composable
fun CryptoDetailScreen(crypto: Crypto) {

    val uriHandler = LocalUriHandler.current

     Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = crypto.image,
            contentDescription = crypto.name,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = crypto.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = crypto.symbol.uppercase()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Current Price: $${crypto.currentPrice}"
        )

        Text(
            text = "24h Change: ${crypto.priceChangePercentage24h}%",
            color = if (crypto.priceChangePercentage24h >= 0)
                Color.Green else Color.Red
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Open CoinGecko",
            color = Color.Blue,
            modifier = Modifier.clickable {
                uriHandler.openUri(crypto.getCoinGeckoUrl())
            }
        )
    }
}