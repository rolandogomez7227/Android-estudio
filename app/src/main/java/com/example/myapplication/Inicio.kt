package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rvRutinas = findViewById<RecyclerView>(R.id.rvRutinas)

        // 1. Cuadrícula de 2 columnas
        rvRutinas.layoutManager = GridLayoutManager(this, 2)

        // 2. Filtro que viene de Categorías
        val filtro = intent.getStringExtra("FILTRO_PARTE")

        val sessionManager = SessionManager(this)
        val tokenGuardado = sessionManager.getToken()

        if (tokenGuardado != null) {
            val authHeader = "Bearer $tokenGuardado"

            // 3. Pasamos el filtro
            Retrofitclient.api.getRutinas(authHeader, filtro).enqueue(object : Callback<ExercisesResponse> {
                override fun onResponse(call: Call<ExercisesResponse>, response: Response<ExercisesResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val listaDeEjercicios = response.body()!!.data
                        val adapter = RutinaAdapter(listaDeEjercicios) { ejercicioTocado ->

                            android.widget.Toast.makeText(this@Inicio, "Tocaste: ${ejercicioTocado.name}", android.widget.Toast.LENGTH_SHORT).show()
                        }
                        rvRutinas.adapter = adapter
                    } else {
                        Toast.makeText(this@Inicio, "Error al cargar la lista", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ExercisesResponse>, t: Throwable) {
                    Toast.makeText(this@Inicio, "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "No hay sesión activa", Toast.LENGTH_LONG).show()
        }
    }
}