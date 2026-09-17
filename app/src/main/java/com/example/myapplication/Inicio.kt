package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rvRutinas = findViewById<RecyclerView>(R.id.rvRutinas)
        rvRutinas.layoutManager = LinearLayoutManager(this)

        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        val gifEjercicio = findViewById<ImageView>(R.id.gifEjercicio)

        Glide.with(this)
            .asGif()
            .load("https://fitcron.com/exercise/press-banca-abierto-con-barra-pectoral/")
            .into(gifEjercicio)

        // Hacemos la petición GET para descargar las rutinas de la base de datos
        api.getRutinas().enqueue(object : Callback<List<Rutina>> {
            override fun onResponse(call: Call<List<Rutina>>, response: Response<List<Rutina>>) {
                if (response.isSuccessful && response.body() != null) {
                    // Si todo sale bien, le pasamos la lista de la base de datos al Adaptador
                    val rutinasDeLaBaseDeDatos = response.body()!!
                    val adapter = RutinaAdapter(rutinasDeLaBaseDeDatos)
                    rvRutinas.adapter = adapter
                } else {
                    Toast.makeText(this@HomeActivity, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                }
            }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}