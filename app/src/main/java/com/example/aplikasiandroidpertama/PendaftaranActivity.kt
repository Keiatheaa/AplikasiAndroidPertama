package com.example.aplikasiandroidpertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PendaftaranActivity : AppCompatActivity() {
    // Deklarasi semua variabel EditText dan Button
    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendaftaran)

        val backLogin = findViewById<Button>(R.id.buttonCancel)
        backLogin.setOnClickListener {
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pendaftaran)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi semua elemen UI dari layout
        editUsername = findViewById(R.id.editTextUsername)
        editEmail = findViewById(R.id.editTextEmail)
        editFirstName = findViewById(R.id.editFirstName)
        editLastName = findViewById(R.id.editLastName)
        editPassword = findViewById(R.id.editTextPassword)
        editConfirmPassword = findViewById(R.id.editTextPassword)
        btnSubmit = findViewById(R.id.buttonSubmit)

        //Menerapkan Logika Validasi saat tombol kirim ditekan
        btnSubmit.setOnClickListener {
            validataForm()
        }

    }
    /**
     * Fungsi untuk memvalidasi semua input field.
     */
    private fun validataForm() {
        val username = editUsername.text.toString().trim()
        val email = editEmail.text.toString().trim()
        val firstName = editFirstName.text.toString().trim()
        val lastName = editLastName.text.toString().trim()
        val password = editPassword.text.toString()
        val confirmPassword = editConfirmPassword.text.toString()

        if(!username.isDigitsOnly()){
            Toast.makeText(this,"Username harus angka!", Toast.LENGTH_LONG).show()
            return
        }

        // 1. Pengecekan Field Kosong
        if (username.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Semua field input wajib diisi!", Toast.LENGTH_LONG).show()
            return
        }

        // 2. Pengecekan Kecocokan Password
        if (password == confirmPassword) {
            // Logika jika semua field terisi dan password cocok.

            val userToSaver = UserEntity (
                namaDepan = firstName,
                namaBelakang = lastName,
                username = username,
                email = email,
                password = password
            )

            val db = AbsenDatabase.getDatabase(this)
            lifecycleScope.launch (Dispatchers.IO){
                db.UserDao().insertUser(userToSaver)
            }

            // Modifikasi Dimulai Di Sini
            val fullName = "$firstName $lastName"
            Toast.makeText(this, "User $fullName berhasil didaftarkan.", Toast.LENGTH_LONG).show()

            editUsername.setText("")
            editEmail.setText("")
            editFirstName.setText("")
            editLastName.setText("")
            editPassword.setText("")
            editConfirmPassword.setText("")



            //Di sini Anda bisa menambahkan fungsi untuk mengirimkan fungsi untuk mengirim data ke server
            //submitFormData()
        } else {
            // Logika jika password tidak cocok
            Toast.makeText(this, "Password dan Konfirmasi Password tidak cocok.", Toast.LENGTH_LONG).show()

            // opsional:kosongkan field konfirmasi password
            editConfirmPassword.setText("")
        }
    }

}