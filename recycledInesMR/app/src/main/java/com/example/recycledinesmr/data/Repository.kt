package com.example.recycledinesmr.data

import android.util.Log
import com.example.recycledinesmr.data.modelo.PeliculaJson
import com.example.recycledinesmr.domain.modelo.Pelicula
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
            Log.d("Repository", "Contenido del archivo JSON: $ejemplo")
            ejemplo?.let { peliculasJson ->  peliculas.addAll(peliculasJson.map { it.toPelicula() }.toList()) }
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
            private val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        }
    }

    companion object {

        private val peliculas = mutableListOf<Pelicula>()

        fun addPelicula(pelicula: Pelicula) =
            peliculas.add(pelicula)

        fun getPelicula(): List<Pelicula> {
            return peliculas
        }

        fun getPeliculaByIndex(index: Int): Pelicula? {
            return if (index >= 0 && index < peliculas.size) {
                peliculas[index]
            } else {
                null
            }
        }

        fun updatePelicula(index: Int, updatedPelicula: Pelicula): Boolean {
            val pelicula = getPeliculaByIndex(index)
            return if (pelicula != null) {
                peliculas[index] = updatedPelicula
                true
            } else {
                false
            }
        }

        fun deletePelicula(index: Int): Boolean {
            val pelicula = getPeliculaByIndex(index)
            return if (pelicula != null) {
                peliculas.removeAt(index)
                true
            } else {
                false
            }

        }

    }
    private val mapPeliculas = mutableMapOf<String, Pelicula>()
}