package com.example.aprobarines.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprobarines.ui.screens.login.PantallaLogin
import com.example.aprobarines.ui.screens.registro.PantallaRegistro
import com.example.aprobarines.utils.Constantes


@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Constantes.LOGIN,
        ){
        composable(
            Constantes.LOGIN
        ){
            PantallaLogin (navController)
        }
        composable(
            Constantes.REGISTRO
        ){
            PantallaRegistro(navController)
        }
    }


}