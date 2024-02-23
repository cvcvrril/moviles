package com.example.composefullequip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aprobarines.ui.common.BottomBar
import com.example.aprobarines.ui.screens.listapersonaje.PantallaListaPersonajes

import com.example.aprobarines.ui.screens.detallepersonaje.PantallaDetallePersonaje
import com.example.aprobarines.ui.screens.pantallaaddpersonaje.PantallaAddPersonaje
import com.example.composefullequip.ui.screens.detalle.PantallaDetalleVideojuego
import com.example.composefullequip.ui.screens.lista.PantallaListaVideojuegos


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "videojuegos",
    ) {
        composable(
            "videojuegos"
        ) {
            PantallaListaVideojuegos(
                onViewDetalle = { id ->
                    navController.navigate("detalle_videojuego/${id}")
                },
                bottomNavigationBar = {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            )
        }
        composable(
            route = "detalle_videojuego/{videojuegoId}",
            arguments = listOf(
                navArgument(name = "videojuegoId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetalleVideojuego(
                videojuegoId = it.arguments?.getString("personaId")?.toInt() ?: 0,
            )
        }
        composable(
            "personajes"
        ) {

            PantallaListaPersonajes(onViewDetalle = { id ->
                navController.navigate("detalle_personaje/${id}")
            }, navController = navController,
                bottomNavigationBar = {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                })

        }
        composable(
            route = "detalle_personaje/{personajeId}",
            arguments = listOf(
                navArgument(name = "personajeId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetallePersonaje(
                personajeId = it.arguments?.getString("personajeId")?.toInt() ?: 0,
            )
        }

        composable(
            route = "add_personaje",
            arguments = listOf(
                navArgument(name = "personajeId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaAddPersonaje(navController = navController)
        }
    }


}
