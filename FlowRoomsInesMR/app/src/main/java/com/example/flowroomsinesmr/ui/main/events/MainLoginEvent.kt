package com.example.flowroomsinesmr.ui.main.events

sealed class MainLoginEvent {

    class GetLogin(val user: String, val password: String) : MainLoginEvent()
    object ErrorVisto: MainLoginEvent()


}