package com.example.flowroomsinesmr.ui.main

sealed class MainEvent {
    class GetLogin(val user: String, val password: String) : MainEvent()


}