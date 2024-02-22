package com.example.aprobarines.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprobarines.ui.common.BottomBar
import com.example.aprobarines.ui.screens.login.PantallaLogin
import com.example.aprobarines.ui.screens.registro.PantallaRegistro
import com.example.composefullequip.ui.navigation.screensBottomBar

@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "registro",
        ){
        composable(
            "login"
        ){
            PantallaLogin {
                bottomNavigationBar = {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            }
        }
        composable(
            "registro"
        ){
            PantallaRegistro {
                bottomNavigationBar = {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            }
        }
    }


}