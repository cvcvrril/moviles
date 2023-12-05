package com.example.practicaexamenmoviles.framework.detallePersonaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaexamenmoviles.domain.usecases.personaje.GetPersonajeUseCase
import com.example.practicaexamenmoviles.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallePersonajeViewModel @Inject constructor(
    private val getPersonajeUseCase: GetPersonajeUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(DetallePersonajeState())
    val uiState: LiveData<DetallePersonajeState> get() = _uiState

    init {
        _uiState.value = DetallePersonajeState(
            error = null,
        )
    }

    fun handleEvent(event: DetallePersonajeEvent) {
        when(event){
            is DetallePersonajeEvent.GetPersonaje -> {
                getPersonaje(event.idPersonaje)
            }
        }
    }

    private fun getPersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            val result = getPersonajeUseCase(idPersonaje)
            when(result){
                is NetworkResult.Error -> _uiState.value = _uiState.value?.copy(error = result.message)
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value?.copy(personaje = result.data)
                }
            }
        }

    }
}