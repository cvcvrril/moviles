package com.example.aprobarines.ui.screens.listapersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.usecases.GetVideojuegoUseCase
import com.example.aprobarines.utils.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaPersonajeViewModel @Inject constructor(
    private val getVideojuegoUseCase: GetVideojuegoUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(PantallaListaPersonajeState())
    val state: StateFlow<PantallaListaPersonajeState> = _state.asStateFlow()

    fun handleEvent(event: PantallaListaPersonajeEvent) {
        when (event) {
            is PantallaListaPersonajeEvent.GetVideojuegos -> getVideojuegos()
            is PantallaListaPersonajeEvent.ErrorVisto -> {
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
            getVideojuegoUseCase.invoke().collect { result ->
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