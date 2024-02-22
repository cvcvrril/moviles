package com.example.composefullequip.ui.navigation





val screensBottomBar = listOf(
    Screens("videojuegos"),
    Screens("personajes"),
    Screens("login"),
    Screens("registro")
)

data class Screens(val route: String) {

}
