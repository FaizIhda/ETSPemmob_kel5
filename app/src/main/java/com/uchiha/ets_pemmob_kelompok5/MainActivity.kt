package com.uchiha.ets_pemmob_kelompok5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uchiha.ets_pemmob_kelompok5.ui.theme.ETS_Pemmob_Kelompok5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETS_Pemmob_Kelompok5Theme {
                // 1. Inisialisasi NavController
                val navController = rememberNavController()

                // 2. NavHost untuk mengatur rute antar file
                NavHost(
                    navController = navController,
                    startDestination = "login_page" // Halaman awal saat aplikasi dibuka
                ) {
                    // Rute ke halaman Login (file login.kt)
                    composable("login_page") {
                        LoginScreen(
                            onNavigateToRegister = { navController.navigate("register_page") }
                        )
                    }

                    // Rute ke halaman Register (file register.kt)
                    composable("register_page") {
                        RegisterScreen(
                            onBackToLogin = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}