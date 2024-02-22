package com.example.aprobarines.ui.screens.registro

import com.example.aprobarines.domain.modelo.User

sealed class PantallaRegistroEvent {
    class IntroducedUsername(val username : String) : PantallaRegistroEvent()
    class IntroducedPassword(val password : String) : PantallaRegistroEvent()
    class DoRegister(val user: User) : PantallaRegistroEvent()


}