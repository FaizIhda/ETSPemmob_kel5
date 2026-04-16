package com.uchiha.ets_pemmob_kelompok5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uchiha.ets_pemmob_kelompok5.ui.theme.ETS_Pemmob_Kelompok5Theme

class Profile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETS_Pemmob_Kelompok5Theme {
                ProfileScreen(
                    onBackToLogin = { finish() }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    onBackToLogin: () -> Unit
) {
    val context = LocalContext.current
    val shopeeOrange = Color(0xFF23C72A)

    // Data dari Register/Login (sesuai field yang ada)
    var fullName by remember { mutableStateOf("Uchiha Sasuke") }
    var usernameEmail by remember { mutableStateOf("sasuke.uchiha@email.com") } // No. HP/Username/Email dari Login
    var memberSince by remember { mutableStateOf("15 Januari 2024") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Foto Profil
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(shopeeOrange.copy(alpha = 0.1f))
                .border(4.dp, shopeeOrange, CircleShape)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Foto Profil",
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.Center),
                tint = shopeeOrange
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nama Lengkap (dari Register)
        Text(
            text = fullName,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Username/Email (dari Login/Register)
        Text(
            text = usernameEmail,
            fontSize = 16.sp,
            color = shopeeOrange,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Card Informasi Profil
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA))
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Informasi Profil",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                ProfileInfoRow(Icons.Default.AccountCircle, "Nama Lengkap", fullName)
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileInfoRow(Icons.Default.Email, "Username/Email", usernameEmail)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol Edit Profil
        Button(
            onClick = {
                Toast.makeText(context, "Fitur Edit Profil", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = shopeeOrange)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Edit Profil", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))


        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun ProfileInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF23C72A),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    ETS_Pemmob_Kelompok5Theme {
        ProfileScreen(onBackToLogin = {})
    }
}