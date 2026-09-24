package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val tvNombre = findViewById<TextView>(R.id.tvDetalleNombre)
        val ivGif = findViewById<ImageView>(R.id.ivDetalleGif)
        val tvInstrucciones = findViewById<TextView>(R.id.tvDetalleInstrucciones)

        val nombre = intent.getStringExtra("NOMBRE") ?: "Sin nombre"
        val imagenUrl = intent.getStringExtra("IMAGEN") ?: ""
        val instrucciones = intent.getStringExtra("INSTRUCCIONES") ?: "Instrucciones no disponibles."

        tvNombre.text = nombre
        tvInstrucciones.text = instrucciones

        // 1. Preparamos el motor de GIFs
        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        // 2. Cargamos la imagen usando el motor
        if (imagenUrl.isNotEmpty()) {
            ivGif.load(imagenUrl, imageLoader) {
                crossfade(true)
            }
        }
    }
}