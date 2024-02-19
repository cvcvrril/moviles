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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaViewModel @Inject constructor(
    private val getVideojuegoUseCase: GetVideojuegoUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(PantallaListaState())
    val state: StateFlow<PantallaListaState> = _state.asStateFlow()

    fun handleEvent(event: PantallaListaEvent) {
        when (event) {
            is PantallaListaEvent.GetVideojuegos -> getVideojuegos()
            is PantallaListaEvent.ErrorVisto -> {
                _state.update { it.copy(error = null) }
            }

            else -> {

            }
        }
    }

    init {
        getVideojuegos()
    }

    private fun getVideojuegos() {
        viewModelScope.launch {
            getVideojuegoUseCase.invoke()
                .collect { result ->
                    _state.update {
                        it.copy(
                            videojuegos = result,
                            error = "Videojuegos cargados",
                        )
                    }
                }
        }
    }


}