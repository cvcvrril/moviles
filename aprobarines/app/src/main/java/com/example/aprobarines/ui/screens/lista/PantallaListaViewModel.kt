package com.example.composefullequip.ui.screens.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.data.repository.ListaRepository
import com.example.aprobarines.domain.usecases.GetVideojuegoUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaViewModel @Inject constructor(
    private val repository: ListaRepository,
    private val getVideojuegoUseCase: GetVideojuegoUseCase,
) : ViewModel() {



    private val _state = MutableStateFlow(PantallaListaState())
    val state: StateFlow<PantallaListaState> = _state.asStateFlow()


    init {
        getVideojuegos()
    }

    private fun getVideojuegos() {
        //TODO: armar método para tomar los videojuegos -> usando flows
    }


}