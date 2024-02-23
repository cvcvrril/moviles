package com.example.aprobarines.ui.navigation

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprobarines.ui.screens.login.PantallaLogin
import com.example.aprobarines.ui.screens.registro.PantallaRegistro

@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "registro",
        ){
        composable(
            "login"
        ){
            PantallaLogin (navController)
        }
        composable(
            "registro"
        ){
            PantallaRegistro(navController)
        }
    }


}