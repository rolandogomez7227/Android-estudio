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

        val tvIrRegistro = findViewById<TextView>(R.id.tvIrRegistro)

        tvIrRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

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
                            Toast.makeText(this@LoginActivity, "¡Sesión iniciada!", Toast.LENGTH_SHORT).show()
                            // Aquí pondremos el código para ir al Home más adelante
                        } else {
                            Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this@LoginActivity, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}