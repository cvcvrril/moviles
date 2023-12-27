package com.example.flowroomsinesmr.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.domain.usecases.credencial.DoRegisterUseCase
import com.example.flowroomsinesmr.domain.usecases.credencial.GetLoginUseCase
import com.example.flowroomsinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase,
    private val doRegisterUseCase: DoRegisterUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    init {
        _uiState.value = MainState(
            error = null,
        )
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetLogin -> getLogin(event.user, event.password)
            is MainEvent.DoRegister -> doRegister(event.credencial)
        }
    }

    private fun doRegister(credencial: Credencial) {
        viewModelScope.launch {
            val result = doRegisterUseCase(credencial)
            when (result) {
                is NetworkResult.Error -> _error.value = result.message ?: "Error"
                is NetworkResult.Success -> result.data?.let {
                    _uiState.value = _uiState.value?.copy(error = "Registro completado")
                }
            }
        }
    }

    private fun getLogin(user: String, password: String) {
        viewModelScope.launch {
            val result = getLoginUseCase(user, password)
            when (result) {
                is NetworkResult.Error -> _error.value = result.message ?: "Error"
                is NetworkResult.Success -> result.data?.let {
                    _uiState.value = _uiState.value?.copy(credencial = result.data)
                }
            }
        }
    }
}