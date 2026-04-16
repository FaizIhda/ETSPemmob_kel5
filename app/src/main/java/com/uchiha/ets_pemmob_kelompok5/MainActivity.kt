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
                            onNavigateToRegister = {
                                navController.navigate("register")
                            },
                            onLoginSuccess = { inputName ->
                                // Mengirim nama ke rute dashboard/{userName}
                                navController.navigate("dashboard/$inputName") {
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
                            onRegisterSuccess = { registeredName ->
                                // Sekarang 'registeredName' berisi variabel 'fullName' dari register.kt
                                navController.navigate("dashboard/$registeredName") {
                                    // Menghapus 'login' dan 'register' dari backstack
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // 3. Rute ke halaman Dashboard
                    composable("dashboard/{userName}") { backStackEntry ->
                        val userName = backStackEntry.arguments?.getString("userName") ?: "Guest"
                        DashboardScreen(
                            userName = userName,
                            onNavigateToProfile = {
                                navController.navigate("profile/$userName")
                            }
                        )
                    }

                    // 4. Rute ke halaman Profile
                    composable("profile/{userName}") { backStackEntry ->
                        val userName = backStackEntry.arguments?.getString("userName") ?: "User"
                        ProfileScreen(
                            userName = userName,
                            onNavigateToDashboard = {
                                navController.popBackStack()
                            },
                            onLogout = {
                                navController.navigate("login") {
                                    // popUpTo(0) memastikan semua screen di stack dihapus
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}