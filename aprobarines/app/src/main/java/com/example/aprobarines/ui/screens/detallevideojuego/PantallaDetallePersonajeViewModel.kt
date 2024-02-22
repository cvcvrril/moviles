package com.example.aprobarines.ui.screens.detallevideojuego

import androidx.lifecycle.ViewModel
import com.example.aprobarines.domain.usecases.GetPersonajesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaDetallePersonajeViewModel @Inject constructor(
    private val getPersonajesUseCase: GetPersonajesUseCase,
) : ViewModel(){

    private val _state = MutableStateFlow(PantallaDetallePersonajeState())
    val state: StateFlow<PantallaDetallePersonajeState> = _state.asStateFlow()

}