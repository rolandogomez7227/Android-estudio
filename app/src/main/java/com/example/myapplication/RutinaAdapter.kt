package com.example.myapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

class RutinaAdapter(
    private val listaEjercicios: List<Rutina>,
    private val onEjercicioClick: (Rutina) -> Unit
) : RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    private var imageLoader: ImageLoader? = null

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
        holder.tvNombre.text = ejercicio.name

        // 1. Inicializamos el motor de GIFs una sola vez de forma segura
        if (imageLoader == null) {
            imageLoader = ImageLoader.Builder(holder.itemView.context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
        }

        // 2. Construimos la petición usando ImageRequest para aplicar el motor de GIFs correctamente
        val request = ImageRequest.Builder(holder.itemView.context)
            .data(ejercicio.image)
            .target(holder.ivImagen)
            .crossfade(true)
            .build()

        // 3. Ejecutamos la carga con el imageLoader personalizado
        imageLoader?.enqueue(request)

        holder.itemView.setOnClickListener {
            onEjercicioClick(ejercicio)
        }
    }

    override fun getItemCount(): Int = listaEjercicios.size
}