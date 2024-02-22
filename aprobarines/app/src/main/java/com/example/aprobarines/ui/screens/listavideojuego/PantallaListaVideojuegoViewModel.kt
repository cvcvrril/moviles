package com.example.composefullequip.ui.screens.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.usecases.GetVideojuegosUseCase
import com.example.aprobarines.utils.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaVideojuegoViewModel @Inject constructor(
    private val getVideojuegosUseCase: GetVideojuegosUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(PantallaListaVideojuegoState())
    val state: StateFlow<PantallaListaVideojuegoState> = _state.asStateFlow()

    fun handleEvent(event: PantallaListaVideojuegoEvent) {
        when (event) {
            is PantallaListaVideojuegoEvent.GetVideojuegos -> getVideojuegos()
            is PantallaListaVideojuegoEvent.ErrorVisto -> {
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
            getVideojuegosUseCase.invoke().collect { result ->
                    when (result) {
                        is NetworkResult.Error -> _state.update {
                            it.copy(
                                error = result.message, isLoading = false
                            )
                        }

                        is NetworkResult.Loading -> _state.update {
                            it.copy(isLoading = false)
                        }

                        is NetworkResult.Success -> _state.update {
                            it.copy(
                                videojuegos = result.data ?: emptyList(),
                                isLoading = false,
                                error = "Videojuegos cargados",
                            )
                        }
                    }
                }
        }
    }


}