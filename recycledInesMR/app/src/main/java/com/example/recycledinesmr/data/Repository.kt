package com.example.recycledinesmr.data

import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.ui.Constantes
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
                101.00f,
                true, false, false, true,
                Constantes.NO_7
            )
        )
        peliculas.add(
            Pelicula(
                "In The Mood For Love",
                "Wong Kar-wai",
                LocalDate.of(2001, 2, 10),
                "Maggie Cheung, Tony Leung Chui-wai, Ping Lam Siu, Joe Cheung, Rebecca Pan",
                5f,
                14.00f,
                false, false, true, true,
                Constantes.NO_7
            )
        )
        peliculas.add(
            Pelicula(
                "Easy A",
                "Will Gluck",
                LocalDate.of(2010, 10, 29),
                "Emma Stone, Pen Badgley, Amanda Bynes, Dan Byrd, Thomas Haden",
                3.5f,
                75.00f,
                true, false, false, false,
                Constantes.NO_16
            )
        )
        peliculas.add(
            Pelicula(
                "Nope",
                "Jordan Peele",
                LocalDate.of(2022, 7, 22),
                "Daniel Kaluuya, Keke Palmer, Brandon Perea, Michael Wincott, Steven Yeun",
                4.6f,
                172.30f,
                false, true, false, false,
                Constantes.NO_12
            )
        )
        peliculas.add(
            Pelicula(
                "The Silence Of The Lambs",
                "Jonathan Demme",
                LocalDate.of(1991, 9, 6),
                "Anthony Hopkins, Jodie Foster, Scott Glenn, Ted Levine, Anthony Heald",
                5f,
                272.70f,
                false, true, false, true,
                Constantes.NO_18
            )
        )
        peliculas.add(
            Pelicula(
                "The cook, the thief, his wife and her lover",
                "Peter Greenaway",
                LocalDate.of(1989, 10, 13),
                "Richard Bohringer, Michael Gambon, Helen Mirren, Alan Howard, Tim Roth",
                2.2f,
                7.70f,
                false, false, false, true,
                Constantes.NO_18
            )
        )
        peliculas.add(
            Pelicula(
                "The Hunt",
                "Thomas Vinterberg",
                LocalDate.of(2012, 4, 19),
                "Mads Mikkelsen, Thomas Bo Larsen, Annika Wedderkopp, Lasse Fogelstøm",
                4.3f,
                18.30f,
                false, false, false, true,
                Constantes.NO_12
            )
        )
    }

    private val mapPeliculas = mutableMapOf<String,Pelicula>()
}