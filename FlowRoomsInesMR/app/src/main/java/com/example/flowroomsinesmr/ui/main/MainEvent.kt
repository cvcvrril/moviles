package com.example.flowroomsinesmr.ui.main

sealed class MainEvent {
    class getLogin(val user: String, val password: String) : MainEvent()


}