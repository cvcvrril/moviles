package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Pelicula

object Repository {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(Pelicula("Litte Miss Sunshine", "Jonathan Dayton/Valerie Faris"))
        peliculas.add(Pelicula("In The Mood For Love", "Wong Kar-wai"))
        peliculas.add(Pelicula("Easy A", "Will Gluck"))
        peliculas.add(Pelicula("Nope", "Jordan Peele"))
        peliculas.add(Pelicula("The Silence Of The Lambs", "Jonathan Demme"))
        peliculas.add(Pelicula("The cook, the thief, his wife and her lover", "Peter Greenaway"))
        peliculas.add(Pelicula("The Hunt", "Thomas Vinterberg"))
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()

    fun addPelicula(pelicula: Pelicula) =
       peliculas.add(pelicula)

    fun getPelicula(): List<Pelicula>{
        return peliculas
    }

}