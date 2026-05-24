package com.example.cryptocurrency_miniproject.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptocurrency_miniproject.models.Crypto
import com.example.cryptocurrency_miniproject.viewmodel.CryptoUIState
import com.example.cryptocurrency_miniproject.viewmodel.CryptoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun CryptoGridScreen(
    uiState: CryptoUIState,
    viewModel: CryptoViewModel,
    onCryptoClick: (Crypto) -> Unit,
    modifier: Modifier = Modifier
) {

    var searchText by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchCoins(it)
            },
            label = { Text("Search crypto") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        //CONFIG depending on the orientation of the screen
        val configuration = LocalConfiguration.current
        val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            4
        } else {
            2
        }

        val dataToShow =
            if (searchText.isEmpty())
                uiState.cryptos
            else
                uiState.searchResults


        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize()
        ) {

            items(dataToShow) { crypto ->
                CryptoGridItem(
                    crypto = crypto,
                    onCryptoClick = onCryptoClick
                )
            }
        }
    }
}

@Composable
fun CryptoGridItem(crypto: Crypto, onCryptoClick: (Crypto) -> Unit) {
    Card(modifier = Modifier.padding(8.dp).clickable { onCryptoClick(crypto) }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = crypto.image,
                contentDescription = crypto.name,
                modifier = Modifier.height(100.dp)
            )
            Text(text = crypto.name)
            Text(text = crypto.symbol.uppercase())
        }
    }
}