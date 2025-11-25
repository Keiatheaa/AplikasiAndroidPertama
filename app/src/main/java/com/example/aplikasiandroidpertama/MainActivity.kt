package com.example.aplikasiandroidpertama

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_main)

        val buttonKirim = findViewById<Button>(R.id.buttonSubmit)

        val inputUsername = findViewById<EditText>(R.id.editTextUsername)
        val inputPassword = findViewById<EditText>(R.id.editTextPassword)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            var username = inputUsername.text.toString()
            var password = inputPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this, "Username / Password tidak boleh kosong",
                    Toast.LENGTH_LONG)
                    .show()

            } else {
                Toast.makeText(
                    this, "Proses login . . . .",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
        val buttonDaftar = findViewById<Button>(R.id.buttonDaftar)

        buttonDaftar.setOnClickListener {
            val intentPindah = Intent(this, PendaftaranActivity::class.java)
            startActivity(intentPindah)
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}