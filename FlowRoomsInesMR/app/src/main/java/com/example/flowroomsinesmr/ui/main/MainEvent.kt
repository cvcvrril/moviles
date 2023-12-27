package com.example.flowroomsinesmr.ui.main

import com.example.flowroomsinesmr.domain.modelo.Credencial

sealed class MainEvent {
    class GetLogin(val user: String, val password: String) : MainEvent()
    class DoRegister(val credencial: Credencial) : MainEvent()


}