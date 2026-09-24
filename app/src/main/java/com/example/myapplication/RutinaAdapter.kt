package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

// 1. Agregamos el parámetro "onEjercicioClick" para detectar el toque
class RutinaAdapter(
    private val listaEjercicios: List<Rutina>,
    private val onEjercicioClick: (Rutina) -> Unit
) : RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    class RutinaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvItemNombre)
        val ivImagen: ImageView = view.findViewById(R.id.ivItemGif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val ejercicio = listaEjercicios[position]

        // Ponemos el texto
        holder.tvNombre.text = ejercicio.name

        // Cargamos la imagen con Coil
        holder.ivImagen.load(ejercicio.image) {
            crossfade(true)
        }

        // 2. NUEVO: Hacemos que toda la tarjeta reaccione al clic
        holder.itemView.setOnClickListener {
            onEjercicioClick(ejercicio)
        }
    }

    override fun getItemCount(): Int = listaEjercicios.size
}