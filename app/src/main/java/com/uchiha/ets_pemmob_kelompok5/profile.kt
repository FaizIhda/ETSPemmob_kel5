package com.uchiha.ets_pemmob_kelompok5

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    userName: String,
    onNavigateToDashboard: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val userPrefs = remember { UserPrefs(context) }
    val (savedUsername, _, savedFullName) = userPrefs.getUser()
    
    val warnautama = Color(0xFF23C72A)

    Scaffold(
        bottomBar = {
            ProfileFooter(
                selectedTab = 2,
                onTabSelected = { index ->
                    if (index == 0) onNavigateToDashboard()
                },
                themeColor = warnautama
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
        ) {
            // --- HEADER MODERN ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(warnautama)
                    .padding(top = 40.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(45.dp).align(Alignment.Center),
                            tint = warnautama
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = savedFullName ?: userName,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Member Silver",
                                color = Color.White,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        Toast.makeText(context, "Pengaturan", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Settings, null, tint = Color.White)
                    }
                }
            }

            // --- SECTION: TRANSAKSI ---
            Card(
                modifier = Modifier.padding(16.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Pesanan Saya", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Lihat Riwayat >", fontSize = 11.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        MarketplaceMenuItem(Icons.Default.ShoppingCart, "Belum Bayar")
                        MarketplaceMenuItem(Icons.Default.Refresh, "Dikemas")
                        MarketplaceMenuItem(Icons.AutoMirrored.Filled.Send, "Dikirim")
                        MarketplaceMenuItem(Icons.Default.Star, "Ulasan")
                    }
                }
            }

            // --- SECTION: INFORMASI PROFIL ---
            Text(
                "Informasi Akun",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Card(
                modifier = Modifier.padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column {
                    ProfileMenuRow(Icons.Default.AccountCircle, "Username", savedFullName ?: userName)
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color(0xFFEEEEEE))
                    ProfileMenuRow(Icons.Default.Email, "Email/No HP", savedUsername ?: "-")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- TOMBOL LOGOUT ---
            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFFFCDD2)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = Color.Red, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Keluar dari Akun", color = Color.Red, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MarketplaceMenuItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = Color.DarkGray, modifier = Modifier.size(26.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(label, fontSize = 10.sp, color = Color.Black)
    }
}

@Composable
fun ProfileMenuRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Aksi edit */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Color(0xFF23C72A), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 14.sp, color = Color.Black)
        Text(value, color = Color.Gray, fontSize = 14.sp)
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = Color.LightGray)
    }
}

@Composable
fun ProfileFooter(selectedTab: Int, onTabSelected: (Int) -> Unit, themeColor: Color) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        val navItems = listOf(
            "Beranda" to Icons.Default.Home,
            "Notifikasi" to Icons.Default.Notifications,
            "Profil" to Icons.Default.AccountCircle
        )
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                label = { Text(item.first, fontSize = 10.sp) },
                icon = { Icon(item.second, contentDescription = item.first) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = themeColor,
                    selectedTextColor = themeColor,
                    indicatorColor = themeColor.copy(alpha = 0.1f),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}