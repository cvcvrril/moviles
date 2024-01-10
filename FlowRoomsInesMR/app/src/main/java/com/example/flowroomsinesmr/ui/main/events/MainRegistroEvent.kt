package com.example.flowroomsinesmr.ui.main.events

import com.example.flowroomsinesmr.domain.modelo.Credencial

sealed class MainRegistroEvent {
    class DoRegister(val credencial: Credencial) : MainRegistroEvent()


}