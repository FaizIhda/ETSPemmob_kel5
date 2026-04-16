package com.uchiha.ets_pemmob_kelompok5

// Import libary
import android.R.attr.text
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    userName: String,
    onNavigateToProfile: () -> Unit) {
    // Melacak produk mana yang sedang diklik user (null = halaman utama)
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    // Melacak posisi navbar yang aktif
    var selectedTab by remember { mutableIntStateOf(0) }
    val primaryGreen = Color(0xFF23C72A)

    // List yg berisi objek produk untuk ditampilkan dengan menggunakan listOf
    val productList = listOf(
        Product(1, "Sepatu Bola Nike Tiempo10", "Rp 1.200.000", "Nike Tiempo Bola, BNIB", "Uchiha Store", "4.8", R.drawable.a1),
        Product(2, "Penggaris Besi Joyko", "Rp 9.000.000", "Penggaris terkuat di bumi", "AKu Cinta Kamu 123", "4.9", R.drawable.a2),
        Product(3, "Hoodie Hitam GAP", "Rp 600.000", "Hoodie import dari mars", "Halo Store", "4.7", R.drawable.a3),
        Product(4, "Tumbler Stanley", "Rp 70.000", "Tumbler berbasis teknologi yg bisa membantu dunia", "MERR Store", "4.5", R.drawable.a4),
        Product(5, "Catur", "Rp 300.000", "Catur berstandar internasional, Import dari sunda empire", "NIchols SPort", "4.6", R.drawable.a5),
        Product(6, "Almet UPNVJT", "Rp 150.000", "Almet kelulusan anak UPNVJT, Auto cumlaude", "Thrift", "4.4", R.drawable.a6)
    )

    // Jika tidak ada produk dipilih, menampilkan Grid. Jika ada, menampilkan Detail.
    if (selectedProduct == null) {
        Scaffold(
            topBar = { DashboardHeader(primaryGreen) },
            bottomBar = {
                DashboardFooter(
                    selectedTab = selectedTab,
                    onTabSelected = { index -> selectedTab = index
                        if (index == 2) {
                            onNavigateToProfile()
                        } // jika user klik icon profile maka akan pindah
                                    },
                    themeColor = primaryGreen
                )
            }
        ) { innerPadding ->
            // Recyclerview: Untuk menampilkan produk
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    ProductItem(product, primaryGreen) {
                        selectedProduct = product // Klik barang untuk masuk detail
                    }
                }
            }
        }
    } else {
        ProductDetailScreen(
            product = selectedProduct!!,
            onBack = { selectedProduct = null }, // Klik kembali untuk reset ke 0 -> halaman utama
            themeColor = primaryGreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(product: Product, onBack: () -> Unit, themeColor: Color) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    // Jika klik iconButton, akan kembali ke halaman sebelumnya -> halaman utama
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Biar bisa di scroll kalau layar penuh
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(16.dp))
            Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(product.price, fontSize = 20.sp, color = themeColor, fontWeight = FontWeight.ExtraBold)

            // Informai rating dan penjual pada detail produk
            Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, null, tint = Color(0xFFFFB400), modifier = Modifier.size(18.dp))
                Text(" ${product.rating} | ", fontWeight = FontWeight.Medium)
                Icon(Icons.Default.Person, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                Text(" ${product.seller}", color = Color.Gray)
            }
            HorizontalDivider(Modifier.padding(vertical = 16.dp))
            Text("Informasi Produk", fontWeight = FontWeight.Bold)
            Text(product.description, fontSize = 14.sp, color = Color.DarkGray)

            Spacer(Modifier.weight(1f)) // Dorong button ke bawah
            Button( // button "beli sekarang"
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 18.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(themeColor)
            ) { Text("Beli Sekarang", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable // search bar dan ikon keranjang dan pesan
fun DashboardHeader(themeColor: Color) {
    // Melacak text (kata kunci) pada pencarian
    var searchText by remember {mutableStateOf("")}

    Row(
        modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText, onValueChange = {newValue -> searchText = newValue},
            placeholder = { Text("Cari produk...", fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            modifier = Modifier.weight(1f).height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = themeColor)
        )
        // Ikon keranjang dan pesan di samping search bar
        IconButton(onClick = {}) { Icon(Icons.Default.ShoppingCart, null, tint = themeColor) }
        IconButton(onClick = {}) { Icon(Icons.Default.Email, null, tint = themeColor) }
    }
}

@Composable
fun ProductItem(product: Product, themeColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = onClick), // Kotak barang bisa diklik
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Image(painterResource(product.imageRes), null, Modifier.fillMaxWidth().height(120.dp), contentScale = ContentScale.Crop)
            Column(Modifier.padding(8.dp)) {
                Text(product.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(product.price, color = themeColor, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)
                Row(Modifier.padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("⭐ ${product.rating}", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text(product.seller, fontSize = 10.sp, color = Color.Gray, maxLines = 1)
                }
            }
        }
    }
}

@Composable
fun DashboardFooter(selectedTab: Int, onTabSelected: (Int) -> Unit, themeColor: Color) {
    NavigationBar(containerColor = Color.White) {
        val navBarang = listOf("Beranda" to Icons.Default.Home, "Notifikasi" to Icons.Default.Notifications, "Profil" to Icons.Default.AccountCircle)
        navBarang.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                label = { Text(item.first) },
                icon = { Icon(item.second, null) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = themeColor,
                    selectedTextColor = themeColor,
                    indicatorColor = themeColor.copy(alpha = 0.1f)
                )
            )
        }
    }
}