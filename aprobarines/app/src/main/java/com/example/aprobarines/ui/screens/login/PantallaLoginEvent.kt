package com.example.aprobarines.ui.screens.login

import com.example.aprobarines.domain.modelo.User

sealed class PantallaLoginEvent {

    class IntroducedUsername(val username: String) : PantallaLoginEvent()
    class IntroducedPassword(val password: String) : PantallaLoginEvent()
    class doLogin(val username: String, val password: String) : PantallaLoginEvent()

}