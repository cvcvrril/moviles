package com.example.recycledinesmr.data

import com.example.recycledinesmr.data.modelo.PeliculaJson
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
import java.time.format.DateTimeFormatter



class Repository(file: InputStream? = null) {

    init {
        if (peliculas.isEmpty()) {
            val moshi = Moshi.Builder()
                .add(LocalDateAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val listOfCardsType: Type = Types.newParameterizedType(
                MutableList::class.java,
                PeliculaJson::class.java
            )

            val json = file?.bufferedReader()?.use { it.readText() }
            val ejemplo = json?.let { contenidoFichero ->
                moshi.adapter<List<PeliculaJson>>(listOfCardsType)
                    .fromJson(contenidoFichero)
            }
            ejemplo?.let { peliculasJson ->
                peliculas.addAll(peliculasJson.map { it.toPelicula() }.toList())
            }
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
            private val FORMATTER = DateTimeFormatter.ofPattern(Constantes.PATTERN)
        }
    }

    fun getLista(): List<Pelicula> {
        return peliculas
    }

    fun updatePelicula(oldPelicula: Pelicula, updatedPelicula: Pelicula) {
        peliculas[peliculas.indexOf(oldPelicula)] = updatedPelicula
    }

    fun deletePelicula(pelicula: Pelicula) = peliculas.remove(pelicula)

    fun addPelicula(pelicula: Pelicula) =
        peliculas.add(pelicula)


    companion object {

        private val peliculas = mutableListOf<Pelicula>()


        fun getPelicula(): List<Pelicula> {
            return peliculas
        }

    }

}