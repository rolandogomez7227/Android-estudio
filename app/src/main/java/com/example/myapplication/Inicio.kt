package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina)

        val gifImageView = findViewById<ImageView>(R.id.gifPressBanca)


        Glide.with(this)
            .asGif()
            .load(R.drawable.press_banca)
            .into(gifImageView)

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