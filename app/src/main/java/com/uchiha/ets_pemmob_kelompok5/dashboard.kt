package com.uchiha.ets_pemmob_kelompok5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uchiha.ets_pemmob_kelompok5.ui.theme.ETS_Pemmob_Kelompok5Theme

class dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ETS_Pemmob_Kelompok5Theme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val primaryGreen = Color(0xFF23C72A)

    // Dummy Data Produk
    val productList = listOf(
        Product(1, "Sepatu Bola Nike Tiempo10", "Rp 1.200.000", "Nike Tiempo Bola, BNIB", "Uchiha Store", "4.8",R.drawable.a1),
        Product(2, "Penggaris Besi Joyko", "Rp 9.000.000", "Penggaris terkuat di bumi", "AKu Cinta Kamu 123", "4.9",R.drawable.a2),
        Product(3, "Hoodie Hitam GAP", "Rp 600.000", "Hoodie import dari mars", "Halo Store", "4.7",R.drawable.a3),
        Product(4, "Tumbler Stanley", "Rp 70.000", "Tumbler berbasis teknologi yg bisa membantu dunia", "MERR Store", "4.5",R.drawable.a4),
        Product(5, "Catur", "Rp 300.000", "Catur berstandar internasional, Import dari sunda empire", "NIchols SPort", "4.6",R.drawable.a5),
        Product(6, "Almet UPNVJT", "Rp 150.000", "Almet kelulusan anak UPNVJT, Auto cumlaude", "Thrift", "4.4",R.drawable.a6)
    )

    Scaffold(
        topBar = { DashboardHeader(primaryGreen) },
        bottomBar = {
            DashboardFooter(selectedTab, onTabSelected = { selectedTab = it }, primaryGreen)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            // "RecyclerView" ala Compose (Grid 2 kolom)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    ProductItem(product, primaryGreen)
                }
            }
        }
    }
}

@Composable
fun DashboardHeader(themeColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Cari produk di sini...", fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = themeColor)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = themeColor)
        Spacer(modifier = Modifier.width(12.dp))
        Icon(Icons.Default.Email, contentDescription = null, tint = themeColor)
    }
}

@Composable
fun ProductItem(product: Product, themeColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // Placeholder Gambar Produk
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Info, contentDescription = null, modifier = Modifier.size(40.dp))
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(product.price, color = themeColor, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Text(product.description, fontSize = 11.sp, color = Color.Gray, maxLines = 2, lineHeight = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = null, Modifier.size(12.dp), tint = Color.Gray)
                    Text(" ${product.seller}", fontSize = 10.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("⭐ ${product.rating}", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun DashboardFooter(selectedTab: Int, onTabSelected: (Int) -> Unit, themeColor: Color) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            Triple("Beranda", Icons.Default.Home, 0),
            Triple("Notifikasi", Icons.Default.Notifications, 1),
            Triple("Profil", Icons.Default.AccountCircle, 2)
        )

        items.forEach { (label, icon, index) ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                label = { Text(label) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = if (selectedTab == index) Modifier.size(30.dp) else Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = themeColor,
                    selectedTextColor = themeColor,
                    indicatorColor = themeColor.copy(alpha = 0.1f)
                )
            )
        }
    }
}