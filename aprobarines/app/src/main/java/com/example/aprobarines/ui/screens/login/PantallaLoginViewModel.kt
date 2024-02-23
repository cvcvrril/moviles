package com.example.aprobarines.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprobarines.domain.modelo.User
import com.example.aprobarines.domain.usecases.DoLoginUseCase
import com.example.aprobarines.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantallaLoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PantallaLoginState())
    val state: StateFlow<PantallaLoginState> = _state.asStateFlow()

    fun handleEvent(event: PantallaLoginEvent) {
        when (event) {
            is PantallaLoginEvent.IntroducedPassword -> {
                introducedPassword(event.password)
            }

            is PantallaLoginEvent.IntroducedUsername -> {
                introducedUsername(event.username)
            }

            is PantallaLoginEvent.doLogin -> {
                doLogin(event.username, event.password)
            }
        }
    }

    private fun doLogin(usernameLogin: String, passwordLogin: String) {
        viewModelScope.launch {
            doLoginUseCase.invoke(usernameLogin, passwordLogin).collect{ result->
                when(result){
                    is NetworkResult.Error -> _state.update {
                        it.copy(
                            error = result.message, isLoading = false
                        )
                    }
                    is NetworkResult.Loading ->  _state.update {
                        it.copy(isLoading = false)
                    }
                    is NetworkResult.Success -> _state.update {
                        it.copy(authResponse = result.data)
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