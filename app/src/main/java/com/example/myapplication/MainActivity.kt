package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvIrRegistro = findViewById<TextView>(R.id.tvIrRegistro)

        tvIrRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()

            if (correo.isNotEmpty() && password.isNotEmpty()) {
                val request = LoginRequest(correo, password)

                Retrofitclient.api.login(request).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 1. Obtenemos el token de la respuesta
                            val token = response.body()?.token

                            // 2. Lo guardamos en el celular
                            if (token != null) {
                                SessionManager(this@LoginActivity).saveToken(token)
                            }

                            Toast.makeText(this@LoginActivity, "¡Sesión iniciada!", Toast.LENGTH_SHORT).show()

                            // Navegar al HomeActivity
                            val intent = Intent(this@LoginActivity, CategoriasActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this@LoginActivity, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}