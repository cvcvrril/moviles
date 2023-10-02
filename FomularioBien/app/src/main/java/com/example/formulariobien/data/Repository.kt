package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Peliculas

object Repository {

    private val peliculas = mutableListOf<Peliculas>()

    init {
        peliculas.add(Peliculas("Litte Miss Sunshine"))
    }

    private val mapPeliculas = mutableMapOf<String, Peliculas>()

    fun addPelicula(peliculas: Peliculas) =
        peliculas.add(peliculas)


}