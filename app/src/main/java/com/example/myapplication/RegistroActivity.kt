package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombreRegistro)
        val etCorreo = findViewById<EditText>(R.id.etCorreoRegistro)
        val etPassword = findViewById<EditText>(R.id.etPasswordRegistro)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmarPassword)
        val btnRegistrarme = findViewById<Button>(R.id.btnRegistrarme)

        btnRegistrarme.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()
            val confirmar = etConfirmar.text.toString()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty()) {
                if (password == confirmar) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val api = retrofit.create(ApiService::class.java)
                    val request = RegisterRequest(nombre, correo, password)

                    api.register(request).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@RegistroActivity, "Cuenta creada", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this@RegistroActivity, "Error al crear", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(this@RegistroActivity, "Error de red", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(this@RegistroActivity, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@RegistroActivity, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}