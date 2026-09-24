package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val tvNombre = findViewById<TextView>(R.id.tvDetalleNombre)
                val ivGif = findViewById<ImageView>(R.id.ivDetalleGif)
                val tvInstrucciones = findViewById<TextView>(R.id.tvDetalleInstrucciones)

                // Atrapamos los datos que nos mande la otra pantalla
                val nombre = intent.getStringExtra("NOMBRE") ?: "Sin nombre"
        val imagenUrl = intent.getStringExtra("IMAGEN") ?: ""
        val instrucciones = intent.getStringExtra("INSTRUCCIONES") ?: "Instrucciones no disponibles."

        // Ponemos los datos en la pantalla
        tvNombre.text = nombre
        tvInstrucciones.text = instrucciones

        if (imagenUrl.isNotEmpty()) {
            ivGif.load(imagenUrl) {
                crossfade(true)
            }
        }
    }
}