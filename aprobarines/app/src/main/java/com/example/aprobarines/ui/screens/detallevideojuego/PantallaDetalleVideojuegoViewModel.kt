package com.example.aprobarines.ui.screens.detallevideojuego

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
class PantallaDetalleVideojuegoViewModel @Inject constructor(
    private val getVideojuegoUseCase: GetVideojuegoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaDetalleVideojuegoState())
    val state : StateFlow<PantallaDetalleVideojuegoState> = _state.asStateFlow()

    fun handleEvent(event: PantallaDetalleVideojuegoEvent){
        when(event){
            PantallaDetalleVideojuegoEvent.ErrorVisto -> {
                _state.update { it.copy(error = null) }
            }
            is PantallaDetalleVideojuegoEvent.GetVideojuego -> getVideojuego(event.id)
        }
    }

    private fun getVideojuego(id: Int) {
        viewModelScope.launch {
            getVideojuegoUseCase.invoke(id).collect{result ->
                when(result){
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
                            videojuego = result.data ?: null,
                            isLoading = false,
                            error = "Personaje cargado",
                        )
                    }
                }
            }
        }
    }


}