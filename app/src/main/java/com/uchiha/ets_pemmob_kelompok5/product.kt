package com.uchiha.ets_pemmob_kelompok5

/**
 * Menggunakan 'data class' karena tujuan utamanya adalah menyimpan data.
 * Otomatis mendapatkan fungsi seperti equals(), hashCode(), dan toString().
 */
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val seller: String,
    val rating: String,
    val imageRes: Int // Untuk menyimpan ID gambar dari drawable
)