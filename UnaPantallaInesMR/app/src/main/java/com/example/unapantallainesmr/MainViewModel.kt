package com.example.unapantallainesmr

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

    private val _text = MutableStateFlow("test")
    val text: StateFlow<String> = _text.asStateFlow()

    fun changeText(texto: String) {
        _text.value = texto
    }


}