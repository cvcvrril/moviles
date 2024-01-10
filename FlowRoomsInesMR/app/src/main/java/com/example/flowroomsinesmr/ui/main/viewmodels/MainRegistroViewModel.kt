package com.example.flowroomsinesmr.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowroomsinesmr.domain.modelo.Credencial
import com.example.flowroomsinesmr.domain.usecases.credencial.DoRegisterUseCase
import com.example.flowroomsinesmr.domain.usecases.credencial.GetLoginUseCase
import com.example.flowroomsinesmr.ui.main.MainEvent
import com.example.flowroomsinesmr.ui.main.MainState
import com.example.flowroomsinesmr.ui.main.events.MainLoginEvent
import com.example.flowroomsinesmr.ui.main.events.MainRegistroEvent
import com.example.flowroomsinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainRegistroViewModel @Inject constructor(
    private val doRegisterUseCase: DoRegisterUseCase
) : ViewModel() {

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

    fun handleEvent(event: MainRegistroEvent) {
        when (event) {
            is MainRegistroEvent.DoRegister -> doRegister(event.credencial)
            is MainLoginEvent.GetLogin -> TODO()
        }
    }

    private fun doRegister(credencial: Credencial) {
        viewModelScope.launch {
            val result = doRegisterUseCase(credencial)
            when (result) {
                is NetworkResult.Error -> _error.value = result.message ?: "Error"
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> result.data?.let {
                    _uiState.value = _uiState.value?.copy(error = "Registro completado")
                    _operacionExitosa.value = true
                }


            }
        }
    }



}