package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Pelicula

object Repository {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(Pelicula("Litte Miss Sunshine"))
        peliculas.add(Pelicula("In The Mood For Love"))
        peliculas.add(Pelicula("Easy A"))
        peliculas.add(Pelicula("Nope"))
        peliculas.add(Pelicula("The Silence Of The Lambs"))
        peliculas.add(Pelicula("The cook, el ladrón, his wife and her lover"))
        peliculas.add(Pelicula("The Hunt"))
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()

    fun addPelicula(pelicula: Pelicula) =
       peliculas.add(pelicula)

    fun getPelicula(): List<Pelicula>{
        return peliculas
    }

}