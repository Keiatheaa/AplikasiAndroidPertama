package com.example.aplikasiandroidpertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val nama_depan = intent.getStringExtra("NAMA_DEPAN")
        val nama_belakang = intent.getStringExtra("NAMA_BELAKANG")
        val username = intent.getIntExtra("USERNAME", 0)
        val email = intent.getStringExtra("EMAIL")

        val tvNamaDepan = findViewById<TextView>(R.id.tvNamaDepan)
        val tvNamaBelakang = findViewById<TextView>(R.id.tvNamaBelakang)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        tvNamaDepan.text = nama_depan
        tvNamaBelakang.text = nama_belakang
        tvUsername.text = username.toString()
        tvEmail.text = email

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}