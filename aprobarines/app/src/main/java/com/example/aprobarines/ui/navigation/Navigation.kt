package com.example.composefullequip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aprobarines.ui.common.BottomBar
import com.example.aprobarines.ui.screens.listapersonaje.PantallaListaPersonajes

import com.example.composefullequip.ui.screens.detalle.PantallaDetallePersonaje
import com.example.composefullequip.ui.screens.detalle.PantallaDetalleVideojuego
import com.example.composefullequip.ui.screens.lista.PantallaListaVideojuegos


@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "videojuegos",
    ) {
        composable(
            "videojuegos"
        ) {
            PantallaListaVideojuegos(
                onViewDetalle = {uuid ->
                    navController.navigate("detalle/${uuid}")
                },
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar)
                }
            )
        }
        composable(
            route =  "detalle/{videojuegoId}",
            arguments = listOf(
                navArgument(name = "videojuegoId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetalleVideojuego(
                videojuegoId = it.arguments?.getString("personaId") ?: "" ,
                )
        }
        composable(
            "personajes"
        ) {

            PantallaListaPersonajes (onViewDetalle = {uuid ->
                navController.navigate("detalle/${uuid}")
            },
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar)
                }) 

        }
        composable(
            route =  "detalle/{personajeId}",
            arguments = listOf(
                navArgument(name = "personajeId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetallePersonaje(
                personajeId = it.arguments?.getString("personajeId") ?: "" ,
            )
        }
    }



}
