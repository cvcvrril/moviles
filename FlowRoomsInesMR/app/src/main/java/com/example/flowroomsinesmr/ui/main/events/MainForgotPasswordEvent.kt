package com.example.flowroomsinesmr.ui.main.events

import com.example.flowroomsinesmr.domain.modelo.Credencial

sealed class MainForgotPasswordEvent {

    class ForgotPassword(val credencial: Credencial) : MainForgotPasswordEvent()


}