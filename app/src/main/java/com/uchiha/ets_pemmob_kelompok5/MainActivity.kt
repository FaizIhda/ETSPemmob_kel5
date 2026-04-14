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
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    // 1. Rute ke halaman Login
                    composable("login") {
                        LoginScreen(
                            // Ke Register jika klik "Daftar"
                            onNavigateToRegister = {
                                navController.navigate("register")
                            },
                            // Ke Dashboard jika klik "Log In"
                            onLoginSuccess = {
                                navController.navigate("dashboard") {
                                    // Hapus halaman login dari history agar tidak bisa back ke login
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 2. Rute ke halaman Register
                    composable("register") {
                        RegisterScreen(
                            onBackToLogin = {
                                navController.popBackStack()
                            },
                            onRegisterSuccess = {
                                // Biasanya setelah daftar langsung masuk dashboard
                                navController.navigate("dashboard") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 3. Rute ke halaman Dashboard
                    composable("dashboard") {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}