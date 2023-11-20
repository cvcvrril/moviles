package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerretrofitinesmr.R
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.ui.main.SwipeGesture

class PeliculaAdapter(
    val context: Context,
    val actions: PeliculaActions
): ListAdapter<Pelicula, PeliculaAdapter.PeliculaViewHolder>(PeliculaDiffCallBack()) {

    interface PeliculaActions{
        fun onDelete(pelicula: Pelicula)
    }

    private var selectedPelicula = mutableListOf<Pelicula>()

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

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    onDelete(currentList[viewHolder.adapterPosition])
                }
            }
        }
    }

    private fun onDelete(pelicula: Pelicula?) {
        val position = currentList.indexOf(pelicula)
        selectedPelicula.remove(pelicula)
        notifyItemRemoved(position)
        if (pelicula != null) {
            actions.onDelete(pelicula)
        }

    }
}