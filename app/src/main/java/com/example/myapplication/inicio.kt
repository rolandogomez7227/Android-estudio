package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class RutinaActivity : AppCompatActivity() {
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


        btnRegresar.setOnClickListener {
            finish()
        }
    }
}