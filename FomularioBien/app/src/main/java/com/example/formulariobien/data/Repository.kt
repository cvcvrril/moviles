package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Pelicula

object Repository {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(Pelicula("Litte Miss Sunshine"))
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()

    fun addPelicula(pelicula: Pelicula) =
       peliculas.add(pelicula)




}