package com.example.cryptocurrency_miniproject.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun CryptoListScreen(viewModel: CryptoViewModel = viewModel()) {

    val cryptos by viewModel.cryptos.collectAsState()

    LazyColumn {
        items(cryptos) { crypto ->

            Card(modifier = Modifier.padding(8.dp)) {

                Row(modifier = Modifier.padding(12.dp)) {

                    AsyncImage(
                        model = crypto.image,
                        contentDescription = crypto.name,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(text = crypto.name)
                        Text(text = crypto.symbol.uppercase())

                        Text(text = "$${crypto.currentPrice}")

                        Text(
                            text = "${crypto.priceChangePercentage24h}%",
                            color = if (crypto.isPositiveChange()) Color.Green else Color.Red
                        )
                    }
                }
            }
        }
    }
}