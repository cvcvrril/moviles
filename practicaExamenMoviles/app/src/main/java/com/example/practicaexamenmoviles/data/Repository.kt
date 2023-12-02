package com.example.practicaexamenmoviles.data

import com.example.practicaexamenmoviles.domain.model.Personaje
import com.example.practicaexamenmoviles.domain.model.Videojuego

class Repository {

    private val videojuegos = mutableListOf<Videojuego>()
    private val  personajes = mutableListOf<Personaje>()

    init {
        videojuegos.add(
            Videojuego(
                id = 1,
                titulo = "Metal Gear Solid 2: Sons of Liberty",
                anyoPublicacion = 2001
            )
        )
        videojuegos.add(
            Videojuego(
                id = 2,
                titulo = "Death Stranding",
                anyoPublicacion = 2019
            )
        )
        videojuegos.add(
            Videojuego(
                id = 3,
                titulo = "Super Mario Galaxy",
                anyoPublicacion = 2019
            )
        )


        personajes.add(
            Personaje(
                id=1,
                name = "Solid Snake",
                idVideojuego = 1
            )
        )
        personajes.add(
            Personaje(
                id=2,
                name = "Solidus Snake",
                idVideojuego = 1
            )
        )
        personajes.add(
            Personaje(
                id=3,
                name = "Otacon",
                idVideojuego = 1
            )
        )
        personajes.add(
            Personaje(
                id=4,
                name = "Revolver Ocelot",
                idVideojuego = 1
            )
        )
        personajes.add(
            Personaje(
                id=5,
                name = "Raiden",
                idVideojuego = 1
            )
        )
        personajes.add(
            Personaje(
                id=6,
                name = "Sam Bridges",
                idVideojuego = 2
            )
        )
        personajes.add(
            Personaje(
                id=7,
                name = "Higgs Monaghan",
                idVideojuego = 2
            )
        )
        personajes.add(
            Personaje(
                id=8,
                name = "Cliff Unger",
                idVideojuego = 2
            )
        )
        personajes.add(
            Personaje(
                id=9,
                name = "Estela",
                idVideojuego = 3
            )
        )
    }


    fun getAllVideojuegos(): List<Videojuego>{
        return videojuegos
    }

    fun getVideojuego(id: Int): Videojuego{
        return videojuegos[id]
    }

    fun addVideojuego(videojuego: Videojuego): Boolean{
        return videojuegos.add(videojuego)
    }

    fun updateVideojuego(videojuego: Videojuego, id: Int): Boolean{
        val videojuegoSelect = videojuegos[id]
        if (videojuegoSelect != null){
            videojuegos[videojuegos.indexOf(videojuegoSelect)] = videojuego
            return true
        }
        return false
    }

    fun deleteVideojuego(id: Int): Boolean{
        if (videojuegos[id] != null){
            videojuegos.removeAt(id)
            return true
        }
        return false
    }

    fun addPersonaje(personaje: Personaje): Boolean{
        return personajes.add(personaje)
    }



}