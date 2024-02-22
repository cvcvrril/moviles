package com.example.aprobarines.ui.screens.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.modelo.User
import com.example.aprobarines.domain.usecases.DoRegistroUseCase
import com.example.aprobarines.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaRegistroViewModel @Inject constructor(
    private val doRegistroUseCase: DoRegistroUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaRegistroState())
    val state: StateFlow<PantallaRegistroState> = _state.asStateFlow()

    fun handleEvent(event: PantallaRegistroEvent) {
        when (event) {
            is PantallaRegistroEvent.IntroducedPassword -> {
                introducedPassword(event.password)
            }

            is PantallaRegistroEvent.IntroducedUsername -> {
                introducedUsername(event.username)
            }

            is PantallaRegistroEvent.DoRegister -> {
                doRegistro(event.user)
            }
        }
    }

    private fun doRegistro(user: User) {
        viewModelScope.launch {
            doRegistroUseCase.invoke(user).collect { result ->
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
                            user = user,
                            isLoading = false,
                            error = "Videojuegos cargados",
                        )
                    }
                }

            }

        }

    }

    private fun introducedUsername(usernameIntroduced: String) {
        _state.update {
            it.copy(
                user = it.user?.copy(username = usernameIntroduced)
                    ?: User(username = usernameIntroduced)
            )
        }
    }

    private fun introducedPassword(passwordIntroduced: String) {
        _state.update {
            it.copy(
                user = it.user?.copy(password = passwordIntroduced)
                    ?: User(password = passwordIntroduced)
            )
        }
    }

}
