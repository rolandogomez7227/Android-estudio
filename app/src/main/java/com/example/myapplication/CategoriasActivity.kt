package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CategoriasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        val btnPecho = findViewById<Button>(R.id.btnPecho)
        val btnEspalda = findViewById<Button>(R.id.btnEspalda)
        val btnBrazos = findViewById<Button>(R.id.btnBrazos)
        val btnPierna = findViewById<Button>(R.id.btnPierna)

        btnPecho.setOnClickListener { abrirLista("chest") }
        btnEspalda.setOnClickListener { abrirLista("back") }
        btnBrazos.setOnClickListener { abrirLista("upper arms") }
        btnPierna.setOnClickListener { abrirLista("upper legs") }
    }

    private fun abrirLista(parteDelCuerpo: String) {
        // Ahora sí, viaja directamente a tu archivo Inicio
        val intent = Intent(this, Inicio::class.java)
        intent.putExtra("FILTRO_PARTE", parteDelCuerpo)
        startActivity(intent)
    }
}