package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Pelicula
import java.sql.Date
import java.time.LocalDate

object Repository {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(Pelicula("Litte Miss Sunshine", "Jonathan Dayton & Valerie Faris", LocalDate.of(2006, 10, 20)))
        peliculas.add(Pelicula("In The Mood For Love", "Wong Kar-wai", LocalDate.of(2001, 2, 10)))
        peliculas.add(Pelicula("Easy A", "Will Gluck", LocalDate.of(2010, 10, 29)))
        peliculas.add(Pelicula("Nope", "Jordan Peele", LocalDate.of(2022, 7, 22)))
        peliculas.add(Pelicula("The Silence Of The Lambs", "Jonathan Demme", LocalDate.of(1991, 9, 6)))
        peliculas.add(Pelicula("The cook, the thief, his wife and her lover", "Peter Greenaway", LocalDate.of(1989, 10, 13)))
        peliculas.add(Pelicula("The Hunt", "Thomas Vinterberg", LocalDate.of(2012, 4, 19)))
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()

    fun addPelicula(pelicula: Pelicula) =
       peliculas.add(pelicula)

    fun getPelicula(): List<Pelicula>{
        return peliculas
    }

}