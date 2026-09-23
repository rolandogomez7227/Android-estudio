package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rvRutinas = findViewById<RecyclerView>(R.id.rvRutinas)
        rvRutinas.layoutManager = LinearLayoutManager(this)

        val sessionManager = SessionManager(this)
        val tokenGuardado = sessionManager.getToken()

        if (tokenGuardado != null) {
            val authHeader = "Bearer $tokenGuardado"

            Retrofitclient.api.getRutinas(authHeader).enqueue(object : Callback<ExercisesResponse> {
                override fun onResponse(call: Call<ExercisesResponse>, response: Response<ExercisesResponse>) {
                    if (response.isSuccessful && response.body() != null) {

                        // Extraemos la lista real que viene dentro de la propiedad "data"
                        val listaDeEjercicios = response.body()!!.data

                        Toast.makeText(this@HomeActivity, "Ejercicios cargados: ${listaDeEjercicios.size}", Toast.LENGTH_SHORT).show()

                        val adapter = RutinaAdapter(listaDeEjercicios)
                        rvRutinas.adapter = adapter
                    } else {
                        val errorMsg = response.errorBody()?.string() ?: "Sin detalles"
                        val codigo = response.code()
                        Toast.makeText(this@HomeActivity, "Error $codigo: $errorMsg", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ExercisesResponse>, t: Throwable) {
                    Toast.makeText(this@HomeActivity, "Fallo de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "No hay sesión activa. Vuelve a iniciar sesión.", Toast.LENGTH_LONG).show()
        }
    }
}