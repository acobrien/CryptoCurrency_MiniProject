package com.example.cryptocurrency_miniproject.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.cryptocurrency_miniproject.models.Crypto
import androidx.compose.foundation.lazy.items

@Composable
fun CryptoDetailScreen(crypto: Crypto, modifier: Modifier = Modifier) {

    val uriHandler = LocalUriHandler.current

     Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
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

        Text(text = crypto.symbol.uppercase())

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Current Price: $${crypto.currentPrice}")

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

         Spacer(modifier = Modifier.height(24.dp))

         Text(
             text = "Videos",
             style = MaterialTheme.typography.titleMedium
         )

         Spacer(modifier = Modifier.height(12.dp))

         VideoList()
    }
}

@Composable
fun VideoList() {
    val videos = listOf(
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
    )

    LazyRow {
        items(videos) { videoUrl ->
            VideoPlayer(videoUrl)
        }
    }
}

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply { player = exoPlayer }
        },
        modifier = Modifier.padding(8.dp).height(200.dp).width(300.dp)
    )
}