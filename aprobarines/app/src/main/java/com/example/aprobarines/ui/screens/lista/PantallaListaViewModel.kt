package com.example.composefullequip.ui.screens.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.data.repository.ListaRepository
import com.example.aprobarines.domain.usecases.GetVideojuegoUseCase
import com.example.aprobarines.utils.NetworkResult

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