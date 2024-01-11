package com.example.flowroomsinesmr.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.domain.usecases.credencial.ForgotPasswordUseCase
import com.example.flowroomsinesmr.ui.main.MainState
import com.example.flowroomsinesmr.ui.main.events.MainForgotPasswordEvent
import com.example.flowroomsinesmr.ui.main.events.MainRegistroEvent
import com.example.flowroomsinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel()  {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState
    private val _operacionExitosa = MutableLiveData<Boolean>()
    val operacionExitosa: LiveData<Boolean> get() = _operacionExitosa

    init {
        _uiState.value = MainState(
            error = null,
        )
    }

    fun handleEvent(event: MainForgotPasswordEvent) {
        when(event){
            is MainForgotPasswordEvent.ForgotPassword -> forgotPassword(event.credencial)
        }
    }

    private fun forgotPassword(credencial: Credencial) {
        viewModelScope.launch {
            val result = forgotPasswordUseCase(credencial)
            when (result) {
                is NetworkResult.Error -> _error.value = result.message ?: "Error"
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> result.data?.let {
                    _uiState.value = _uiState.value?.copy(error = "Email enviado")
                    _operacionExitosa.value = true
                }


            }
        }
    }

}