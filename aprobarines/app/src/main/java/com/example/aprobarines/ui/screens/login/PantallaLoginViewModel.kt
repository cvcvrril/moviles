package com.example.aprobarines.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.composefullequip.data.ListaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaLoginViewModel  @Inject constructor(
    private val repository: ListaRepository
): ViewModel(){

    private val _state = MutableStateFlow(PantallaLoginState())
    val state : StateFlow<PantallaLoginState> = _state.asStateFlow()

}