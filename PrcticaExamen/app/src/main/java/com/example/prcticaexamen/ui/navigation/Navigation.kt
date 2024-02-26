package com.example.composefullequip.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprobarines.ui.common.BottomBar


import com.example.prcticaexamen.ui.screens.listamapa.PantallaListaMapas


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "mapas",
    ) {
        composable(
            "mapas"
        ) {
            PantallaListaMapas(
                onViewDetalle = { id ->
                    navController.navigate("detalle_videojuego/${id}")
                }
            ) {
                BottomBar(
                    navController = navController,
                    screens = screensBottomBar
                )
            }
        }
    }
}
