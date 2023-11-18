package com.example.recyclerretrofitinesmr.ui.main

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerretrofitinesmr.R
import com.example.recyclerretrofitinesmr.databinding.ViewDirectorBinding
import com.example.recyclerretrofitinesmr.domain.Director

class DirectorAdapter(
    val context: Context,
    val actions: DirectorActions
) :
    ListAdapter<Director, DirectorAdapter.ItemViewholder>(DiffCallback()) {

    interface DirectorActions {
        fun onDelete(director: Director)
        fun onStartSelectedMode(director: Director)
        fun itemClicked(director: Director)
    }

    private var selectedDirectores = mutableListOf<Director>()
    private var selectedMode: Boolean = false

    fun startSelectMode() {
        selectedMode = true
    }

    fun resetSelectMode() {
        selectedMode = false
        selectedDirectores.clear()
    }

    fun setSelectedItems(personasSeleccionadas: List<Director>) {
        selectedDirectores.clear()
        selectedDirectores.addAll(personasSeleccionadas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        Log.d("DirectorAdapter", "onCreateViewHolder called")
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_director, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int){
        val director = getItem(position)
        director?.let {
            holder.bind(it)
        }
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewDirectorBinding.bind(itemView)

        fun bind(item: Director) {
            Log.d("Directores(DirectorAdapter)", "Binding director: ${item.id}, isSelected: ${item.isSelected}")
            itemView.setOnClickListener {
                if (!selectedMode){
                    actions.onStartSelectedMode(item)
                }
                true
            }
            with(binding) {
                selected.setOnClickListener {
                    if (selectedMode) {
                        if (binding.selected.isChecked) {
                            item.isSelected = true
                            itemView.setBackgroundColor(Color.GRAY)
                            selectedDirectores.add(item)
                        } else {
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.WHITE)
                            selectedDirectores.remove(item)
                        }
                        actions.itemClicked(item)
                    }
                }
                tvNombre.text = item.nombre
                tvId.text = item.id.toString()

                if (selectedMode){
                    selected.visibility =View.VISIBLE
                } else{
                    item.isSelected= false
                    selected.visibility = View.GONE
                }
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Director>() {
        override fun areItemsTheSame(oldItem: Director, newItem: Director): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Director, newItem: Director): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    selectedDirectores.remove(currentList[viewHolder.adapterPosition])
                    actions.onDelete(currentList[viewHolder.adapterPosition])
                    if (selectedMode)
                        actions.itemClicked(currentList[viewHolder.adapterPosition])
                }
            }
        }
    }

}