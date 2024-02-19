package com.example.composefullequip.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aprobarines.ui.common.BottomBar

import com.example.composefullequip.ui.screens.detalle.PantallaDetalle
import com.example.composefullequip.ui.screens.lista.PantallaLista


@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "listado",
    ) {
        composable(
            "listado"
        ) {
            PantallaLista(
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
            route =  "detalle/{personaId}",
            arguments = listOf(
                navArgument(name = "personaId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetalle(
                personaId = it.arguments?.getString("personaId") ?: "" ,
                )
        }
        composable(
            "pantalla"
        ) {

            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold (
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar =  { BottomBar(
                    navController = navController,
                    screens = screensBottomBar) },

            ) { innerPadding ->
                Text(
                    modifier = Modifier.padding(
                        innerPadding),
                    text = "pantalla")


            }

        }
    }



}
