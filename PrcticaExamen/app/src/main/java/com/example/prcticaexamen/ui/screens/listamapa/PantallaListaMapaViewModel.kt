package com.example.prcticaexamen.ui.screens.listamapa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prcticaexamen.domain.usecases.GetAllMapasUseCase
import com.example.prcticaexamen.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaListaMapaViewModel @Inject constructor(
    private val getAllMapasUseCase: GetAllMapasUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaListaMapaState())
    val state: StateFlow<PantallaListaMapaState> = _state.asStateFlow()

    fun handleEvent(event: PantallaListaMapaEvent) {
        when (event) {
            is PantallaListaMapaEvent.DeletePersonaje -> TODO()
            PantallaListaMapaEvent.ErrorVisto -> {
                _state.update { it.copy(error = null) }

            }
            PantallaListaMapaEvent.GetMapas -> getAllMapas()
        }


    }

    init {
        getAllMapas()
    }

    private fun getAllMapas() {
        viewModelScope.launch {
            getAllMapasUseCase.invoke().collect { result ->
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
                            mapas = result.data ?: emptyList(),
                            isLoading = false,
                            error = "Personajes cargados",
                        )
                    }
                }

            }
        }
    }

}