package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()

            if (correo.isNotEmpty() && password.isNotEmpty()) {
                val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/")
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build()

                val api = retrofit.create(ApiService::class.java)
                val request = LoginRequest(correo, password)

                api.login(request).enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<LoginResponse>,
                        response: retrofit2.Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            android.widget.Toast.makeText(
                                this@LoginActivity,
                                "¡Sesión iniciada!",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            android.widget.Toast.makeText(
                                this@LoginActivity,
                                "Credenciales incorrectas",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                        android.widget.Toast.makeText(
                            this@LoginActivity,
                            "Error de red: ${t.message}",
                            android.widget.Toast.LENGTH_LONG
                        ).show()
                    }
                })
            } else {
                android.widget.Toast.makeText(
                    this@LoginActivity,
                    "Llena todos los campos",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}