package com.example.aprobarines.ui.screens.detallepersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.usecases.GetPersonajeUseCase
import com.example.aprobarines.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaDetallePersonajeViewModel @Inject constructor(
    private val getPersonajeUseCase: GetPersonajeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaDetallePersonajeState())
    val state: StateFlow<PantallaDetallePersonajeState> = _state.asStateFlow()

    fun handleEvent(event: PantallaDetallePersonajeEvent) {
        when (event) {
            PantallaDetallePersonajeEvent.ErrorVisto -> {
                _state.update { it.copy(error = null) }
            }

            is PantallaDetallePersonajeEvent.GetPersonaje -> getPersonaje(event.id )
        }
    }

    private fun getPersonaje(id: Int) {
        viewModelScope.launch {
            getPersonajeUseCase.invoke(id).collect{result ->
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
                            personaje = result.data ?: null,
                            isLoading = false,
                            error = "Personaje cargado",
                        )
                    }
                }
            }
        }
    }


}