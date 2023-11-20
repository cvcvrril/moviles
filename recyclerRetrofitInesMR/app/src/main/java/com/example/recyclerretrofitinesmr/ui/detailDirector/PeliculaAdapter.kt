package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerretrofitinesmr.R
import com.example.recyclerretrofitinesmr.domain.Pelicula

class PeliculaAdapter: ListAdapter<Pelicula, PeliculaAdapter.PeliculaViewHolder>(PeliculaDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = getItem(position)
        holder.bind(pelicula)
    }

    inner class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.tvId)
        private val nombreTextView: TextView = itemView.findViewById(R.id.tvNombre)

        fun bind(pelicula: Pelicula) {
            idTextView.text = pelicula.id.toString()
            nombreTextView.text = pelicula.titulo
        }
    }

     class PeliculaDiffCallBack : DiffUtil.ItemCallback<Pelicula>() {
        override fun areItemsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem == newItem
        }
    }
}