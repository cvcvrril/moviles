package com.example.aprobarines.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "login",
        ){
        composable(
            "login"
        ){

        }
        composable(
            "registro"
        ){

        }
    }


}