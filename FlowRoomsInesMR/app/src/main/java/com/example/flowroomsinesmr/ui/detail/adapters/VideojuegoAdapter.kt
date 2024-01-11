package com.example.flowroomsinesmr.ui.detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowroomsinesmr.R
import com.example.flowroomsinesmr.databinding.VideojuegoViewBinding
import com.example.flowroomsinesmr.domain.modelo.Videojuego

class VideojuegoAdapter(
    val context: Context
) : ListAdapter<Videojuego, VideojuegoAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.videojuego_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = VideojuegoViewBinding.bind(itemView)

        fun bind(item: Videojuego) {
            binding.textTitulo.setText(item.titulo)

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Videojuego>() {
        override fun areItemsTheSame(oldItem: Videojuego, newItem: Videojuego): Boolean {
            return oldItem.titulo == newItem.titulo
        }

        override fun areContentsTheSame(oldItem: Videojuego, newItem: Videojuego): Boolean {
            return oldItem == newItem
        }
    }


}