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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
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

        val db = AbsenDatabase.getDatabase(this)
        userDao = db.UserDao()

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
                lifecycleScope.launch(Dispatchers.IO){
                    val user= userDao.getUserByUsernameAndPassword(username, password)
                    withContext(Dispatchers.Main) {
                        if (user != null) {
                            //Toast.makeText(this@MainActivity, "User ditemukan", Toast.LENGTH_LONG) .show()

                            val intentPindahDashboard  = Intent(this@MainActivity, DashboardActivity::class.java)

                            intentPindahDashboard.putExtra("ID", user.id)

                            startActivity(intentPindahDashboard)
                        } else {
                            Toast.makeText(
                                this@MainActivity, "Username / Password Salah",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }
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