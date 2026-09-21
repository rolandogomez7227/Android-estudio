package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RutinaAdapter(private val rutinas: List<Rutina>) : RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    class RutinaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivItemGif: ImageView = view.findViewById(R.id.ivItemGif)
        val tvItemNombre: TextView = view.findViewById(R.id.tvItemNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        // Aquí cargamos tu "molde" item_rutina.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, position: Int) {
        val rutina = rutinas[position]
        holder.tvItemNombre.text = rutina.name // Ponemos el nombre que viene de la BD

        // Reproducimos el GIF. Por ahora, usaremos tu GIF local para probar que la lista funciona.
        Glide.with(holder.itemView.context)
            .load(R.drawable.press_banca)
            .into(holder.ivItemGif)
    }

    override fun getItemCount() = rutinas.size
}