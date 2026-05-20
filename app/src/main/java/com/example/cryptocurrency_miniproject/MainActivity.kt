package com.example.cryptocurrency_miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency_miniproject.Routes.Routes
import com.example.cryptocurrency_miniproject.ui.theme.CryptoCurrency_MiniProjectTheme
import com.example.cryptocurrency_miniproject.ui.theme.screens.CryptoDetailScreen
import com.example.cryptocurrency_miniproject.ui.theme.screens.CryptoListScreen
import com.example.cryptocurrency_miniproject.viewmodel.CryptoViewModel
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrency_MiniProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CryptoCurrencyAppStart(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CryptoCurrencyAppStart(name: String, modifier: Modifier = Modifier) {
    val viewModel: CryptoViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CryptoTopBar(navController = navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.LIST,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Routes.LIST) {

                CryptoListScreen(
                    uiState = uiState,
                    viewModel = viewModel,
                    onCryptoClick = { crypto ->
                        viewModel.setSelectedCrypto(crypto)
                        navController.navigate(Routes.DETAIL)
                    }
                )
            }

            composable(Routes.DETAIL) {

                uiState.selectedCrypto?.let { crypto ->

                    CryptoDetailScreen(
                        crypto = crypto
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoTopBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isOnList = currentRoute == Routes.LIST
    val isOnDetail = currentRoute == Routes.DETAIL

    CenterAlignedTopAppBar(
        title = { Text("CryptoTracker") },
        navigationIcon = {
            when {
                isOnDetail -> {
                    IconButton(onClick = { navController.popBackStack() } ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

                isOnList -> {
                    Icon(Icons.Default.Home, contentDescription = null)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CryptoCurrency_MiniProjectTheme {
    }
}