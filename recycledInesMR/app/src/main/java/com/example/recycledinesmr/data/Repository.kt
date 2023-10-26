package com.example.recycledinesmr.data

import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.ui.Constantes
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Repository(file: InputStream? = null) {

    private val peliculas = mutableListOf<Pelicula>()

    init {
        peliculas.add(
            Pelicula(
                "Litte Miss Sunshine",
                "Jonathan Dayton & Valerie Faris",
                LocalDate.of(2006, 10, 20),
                4.5f,
                Constantes.NO_7
            )
        )
        peliculas.add(
            Pelicula(
                "In The Mood For Love",
                "Wong Kar-wai",
                LocalDate.of(2001, 2, 10),
                5f,
                Constantes.NO_7
            )
        )
        peliculas.add(
            Pelicula(
                "Easy A",
                "Will Gluck",
                LocalDate.of(2010, 10, 29),
                3.5f,
                Constantes.NO_16
            )
        )
        peliculas.add(
            Pelicula(
                "Nope",
                "Jordan Peele",
                LocalDate.of(2022, 7, 22),
                4.6f,
                Constantes.NO_12
            )
        )
        peliculas.add(
            Pelicula(
                "The Silence Of The Lambs",
                "Jonathan Demme",
                LocalDate.of(1991, 9, 6),
                5f,
                Constantes.NO_18
            )
        )
        peliculas.add(
            Pelicula(
                "The cook, the thief, his wife and her lover",
                "Peter Greenaway",
                LocalDate.of(1989, 10, 13),
                2.2f,
                Constantes.NO_18
            )
        )
        peliculas.add(
            Pelicula(
                "The Hunt",
                "Thomas Vinterberg",
                LocalDate.of(2012, 4, 19),
                4.3f,
                Constantes.NO_12
            )
        )

        if (peliculas.size == 0) {
            val moshi = Moshi.Builder()
                .add(LocalDateAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = Types.newParameterizedType(
                MutableList::class.java,
                Pelicula::class.java
            )
            val ejemplo = file?.bufferedReader()?.readText()?.let { contenidoFichero ->
                moshi.adapter<List<Pelicula>>(listOfCardsType)
                    .fromJson(contenidoFichero)
            }
            ejemplo?.let { peliculas.addAll(it) }
        }


    }

    class LocalDateAdapter {
        @ToJson
        fun toJson(value: LocalDate): String {
            return FORMATTER.format(value)
        }

        @FromJson
        fun fromJson(value: String): LocalDate {
            return LocalDate.from(FORMATTER.parse(value))
        }

        companion object {
            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss-SSSS")
        }
    }


    fun getLista(): List<Pelicula> {
        return peliculas.map { it.toPelicula() }
    }

    private val mapPeliculas = mutableMapOf<String, Pelicula>()
}