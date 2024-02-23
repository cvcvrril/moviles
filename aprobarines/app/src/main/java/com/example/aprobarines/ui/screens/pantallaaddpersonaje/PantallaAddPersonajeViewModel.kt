package com.example.aprobarines.ui.screens.pantallaaddpersonaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.modelo.Personaje
import com.example.aprobarines.domain.modelo.User
import com.example.aprobarines.domain.usecases.AddPersonajeUseCase
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
class PantallaAddPersonajeViewModel @Inject constructor(
    private val addPersonajeUseCase: AddPersonajeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaAddPersonajeState())
    val state: StateFlow<PantallaAddPersonajeState> = _state.asStateFlow()

    fun handleEvent(event: PantallaAddPersonajeEvent) {
        when (event) {
            is PantallaAddPersonajeEvent.AddPersonaje -> addPersonaje(event.nombre)
            PantallaAddPersonajeEvent.ErrorVisto -> {
                _state.update { it.copy(error = null) }
            }

            is PantallaAddPersonajeEvent.IntroducedNombre -> introducedName(event.nombre)
        }
    }

    private fun introducedName(nameIntroduced: String) {
        _state.update {
            it.copy(
                personaje = it.personaje?.copy(nombre = nameIntroduced)
                    ?: Personaje(nombre = nameIntroduced)
            )
        }
    }


    private fun addPersonaje(nombre: String) {
        viewModelScope.launch {
            addPersonajeUseCase.invoke(nombre).collect { result ->
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