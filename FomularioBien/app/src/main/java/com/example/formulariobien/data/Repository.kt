package com.example.formulariobien.data

import com.example.formulariobien.domain.modelo.Pelicula
import java.sql.Date
import java.time.LocalDate

object Repository {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(
            Pelicula(
                "Litte Miss Sunshine",
                "Jonathan Dayton & Valerie Faris",
                LocalDate.of(2006, 10, 20),
                "Steve Carrell, Paul Dano, Greg Kinnear, Bryan Cranston, Toni Collette",
                4.5f,
                8,
                true, false, false, false
            )
        )
        peliculas.add(
            Pelicula(
                "In The Mood For Love",
                "Wong Kar-wai",
                LocalDate.of(2001, 2, 10),
                "Maggie Cheung, Tony Leung Chui-wai, Ping Lam Siu, Joe Cheung, Rebecca Pan",
                5f
            )
        )
        peliculas.add(
            Pelicula(
                "Easy A",
                "Will Gluck",
                LocalDate.of(2010, 10, 29),
                "Emma Stone, Pen Badgley, Amanda Bynes, Dan Byrd, Thomas Haden",
                3.5f
            )
        )
        peliculas.add(
            Pelicula(
                "Nope",
                "Jordan Peele",
                LocalDate.of(2022, 7, 22),
                "Daniel Kaluuya, Keke Palmer, Brandon Perea, Michael Wincott, Steven Yeun",
                4.6f
            )
        )
        peliculas.add(
            Pelicula(
                "The Silence Of The Lambs",
                "Jonathan Demme",
                LocalDate.of(1991, 9, 6),
                "Anthony Hopkins, Jodie Foster, Scott Glenn, Ted Levine, Anthony Heald",
                5f
            )
        )
        peliculas.add(
            Pelicula(
                "The cook, the thief, his wife and her lover",
                "Peter Greenaway",
                LocalDate.of(1989, 10, 13),
                "Richard Bohringer, Michael Gambon, Helen Mirren, Alan Howard, Tim Roth",
                2.2f
            )
        )
        peliculas.add(
            Pelicula(
                "The Hunt",
                "Thomas Vinterberg",
                LocalDate.of(2012, 4, 19),
                "[REDACTED], Thomas Bo Larsen, Annika Wedderkopp, Lasse Fogelstøm",
                4.3f
            )
        )
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()

    fun addPelicula(pelicula: Pelicula) =
        peliculas.add(pelicula)

    fun getPelicula(): List<Pelicula> {
        return peliculas
    }

}