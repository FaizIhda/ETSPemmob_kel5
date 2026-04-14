package com.uchiha.ets_pemmob_kelompok5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uchiha.ets_pemmob_kelompok5.ui.theme.ETS_Pemmob_Kelompok5Theme

class login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETS_Pemmob_Kelompok5Theme {
                LoginScreen(onNavigateToRegister = {})
            }
        }
    }
}

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Warna Aplikasi
    val shopeeOrange = Color(0xFF23C72A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Placeholder Logo (Ganti dengan R.drawable.logo_kamu)
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_send),
            contentDescription = "Logo",
            modifier = Modifier.size(80.dp),
            tint = shopeeOrange
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Input Username/No HP
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("No. HP / Username / Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = shopeeOrange,
                focusedLabelColor = shopeeOrange
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = shopeeOrange,
                focusedLabelColor = shopeeOrange
            )
        )

        // Lupa Password
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            TextButton(onClick = { /* Handle Lupa Password */ }) {
                Text("Lupa Password?", color = Color(0xFF008BFF))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Login
        Button(
            onClick = {
                Toast.makeText(context, "Mencoba Login...", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = shopeeOrange)
        ) {
            Text("Log In", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider atau
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color.LightGray)
            Text("  ATAU  ", color = Color.Gray, fontSize = 12.sp)
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color.LightGray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Alternatif Login (Google/Facebook style)
        OutlinedButton(
            onClick = { /* Login Google */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Lanjutkan dengan Google", color = Color.Black)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer Daftar
        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Baru di Marketplace?")
            TextButton(onClick = { onNavigateToRegister() }) {
                Text("Daftar", color = shopeeOrange, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    ETS_Pemmob_Kelompok5Theme {
        LoginScreen(onNavigateToRegister = {})
    }
}