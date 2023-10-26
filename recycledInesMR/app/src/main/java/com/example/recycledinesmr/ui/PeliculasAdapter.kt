package com.example.recycledinesmr.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycledinesmr.R
import com.example.recycledinesmr.databinding.ItemPeliculaBinding
import com.example.recycledinesmr.domain.modelo.Pelicula


class PeliculasAdapter(
    private var peliculas: List<Pelicula>,
    private val onClickBotton: (String) -> Unit
) : RecyclerView.Adapter<PeliculasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater = LayoutInflater. from(parent.context)
        return PeliculasViewHolder(layoutInflater.inflate(R.layout.item_pelicula, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.render(peliculas[position], onClickBotton)
    }

    override fun getItemCount(): Int = peliculas.size
}

class PeliculasViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding =ItemPeliculaBinding.bind(view)

    fun render(pelicula: Pelicula, onClickBotton: (String) -> Unit){
        with(binding){
            tvTitulo.setText(pelicula.titulo)
            tvDirector.setText(pelicula.director)
        }

        view.findViewById<TextView>(R.id.buttonNext).setOnClickListener{
            onClickBotton(pelicula.titulo)
        }

    }


}

