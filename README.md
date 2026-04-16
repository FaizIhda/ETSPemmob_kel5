Judul: Tugas Evaluasi Tengah Semester (ETS) - Pemrograman Perangkat Bergerak
Deskripsi Aplikasi
kubeli adalah aplikasi Android berbasis modern yang dibangun menggunakan Jetpack Compose.
Aplikasi ini menerapkan standar UI/UX modern dengan Material Design 3, memastikan antarmuka yang bersih, responsif, dan nyaman digunakan di berbagai ukuran layar perangkat Android.
Daftar Fitur Utama
1. Multi-Screen Navigation: Perpindahan antar halaman yang mulus.
2. Modern UI Components: Menggunakan komponen Material 3 (TopAppBar, Cards, FloatingActionButton, dll).
3. Data Display (List/Grid): Menampilkan sekumpulan data secara efisien.
4. Detail Information: Halaman khusus untuk melihat informasi rinci dari item tertentu.
5. State Management: Pengelolaan data yang responsif terhadap perubahan layar (Recomposition).
Deskripsi Fitur
1. Navigasi Antar Halaman (Jetpack Navigation)
Fitur ini memungkinkan pengguna untuk berpindah dari satu layar ke layar lain (misalnya dari halaman Beranda ke halaman Detail) tanpa kehilangan konteks. Menggunakan navigation-compose untuk menangani backstack dengan efisien.
2. Antarmuka Material 3
Seluruh elemen visual aplikasi menggunakan library androidx.compose.material3. Fitur ini mencakup penggunaan skema warna yang dinamis, tipografi yang jelas, dan komponen interaktif yang mengikuti panduan desain terbaru dari Google.
3. Tampilan List yang Responsif (Lazy Layout)
Aplikasi mampu menampilkan daftar data (seperti daftar produk/berita/kontak) menggunakan LazyColumn atau LazyGrid. Fitur ini memastikan performa aplikasi tetap ringan meskipun data yang ditampilkan berjumlah banyak.
4. Halaman Detail Dinamis
Saat pengguna memilih salah satu item dari daftar, aplikasi akan mengarahkan ke halaman detail. Fitur ini menangani pengiriman data (argumen) antar layar untuk menampilkan informasi yang lebih spesifik dan mendalam.

Teknologi yang Digunakan :
•Bahasa: Kotlin
•UI Framework: Jetpack Compose
•Design System: Material Design 3
•Navigation: Compose Navigation
•Lifecycle: ViewModel & LiveData/State
