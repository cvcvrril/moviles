package com.example.recyclerretrofitinesmr.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.usecases.director.DeleteDirectorUseCase
import com.example.recyclerretrofitinesmr.domain.usecases.director.GetAllDirectorUseCase
import com.example.recyclerretrofitinesmr.domain.usecases.director.GetDirectorUseCase
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllDirectorUseCase: GetAllDirectorUseCase,
    private val getDirectorUseCase: GetDirectorUseCase,
    private val deleteDirectorUseCase: DeleteDirectorUseCase
) :
    ViewModel() {

    private val listaDirectores = mutableListOf<Director>()
    private var selectedDirectores = mutableListOf<Director>()
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.DeleteDirector -> {
                deleteDirector(event.director)
            }

            MainEvent.GetAllDirectores -> {
                getAllDirectores()
                Log.d("Directores (MainViewModel2)", "Directores: ${getAllDirectores()}")
            }

            is MainEvent.GetDirector -> {
                getDirector(event.id)
            }

            is MainEvent.DeleteDirectoresSeleccionados -> {
                _uiState.value?.let {
                    deleteDirector(it.directoresSeleccionados)
                    resetSelectMode()
                }
            }

            MainEvent.ErrorVisto -> {
                _uiState.value = _uiState.value?.copy(error = null)
            }

            MainEvent.ResetSelectMode -> {
                resetSelectMode()
            }

            is MainEvent.SeleccionaDirector -> {
                seleccionaPersona(event.director)
            }

            MainEvent.StartSelectMode -> {
                _uiState.value = _uiState.value?.copy(selectMode = true)
            }
        }

    }


    private fun resetSelectMode() {
        selectedDirectores.clear()
        _uiState.value =
            _uiState.value?.copy(selectMode = false, directoresSeleccionados = selectedDirectores)

    }

    private fun getAllDirectores() {
        viewModelScope.launch {
            val result = getAllDirectorUseCase.getAllDirector()
            Log.d("Directores (MainViewModel1)", "Directores: ${result}")
            when (result) {
                is NetworkResult.Success -> {
                    listaDirectores.clear()
                    listaDirectores.addAll(result.data as List<Director>)
                    _uiState.value = _uiState.value?.copy(directores = listaDirectores)

                }

                is NetworkResult.Error -> {
                    _error.value = result.message.toString()
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }


    private fun getDirector(id: String) {
        viewModelScope.launch {
            val result = getDirectorUseCase.getDirector(id)
            when (result) {
                is NetworkResult.Success -> {
                    val director = result.data
                    _uiState.value = _uiState.value?.copy(selectedDirector = director)
                }

                is NetworkResult.Error -> {
                    _error.value = result.message.toString()
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }

    private fun deleteDirector(director: Director) {
        viewModelScope.launch {
            val result = deleteDirectorUseCase.deleteDirector(director.id.toString())
            when (result) {
                is NetworkResult.Success -> {
                    listaDirectores.removeAll { it.id == director.id }
                    _uiState.value = _uiState.value?.copy(directores = listaDirectores)
                    Log.d("DelDirectores (MainViewModel1)", "Directores: ${listaDirectores}")
                }

                is NetworkResult.Error -> {
                    _error.value = result.message.toString()
                }

                is NetworkResult.Loading -> TODO()
            }
        }

    }

    private fun deleteDirector(directores: List<Director>) {
        viewModelScope.launch {
            listaDirectores.removeAll(directores)
            selectedDirectores.removeAll(directores)
            _uiState.value =
                _uiState.value?.copy(directoresSeleccionados = selectedDirectores.toList())
            getAllDirectores()
        }
    }

    private fun seleccionaPersona(director: Director) {
        if (isSelected(director)) {
            selectedDirectores.remove(director)

        } else {
            selectedDirectores.add(director)
        }
        _uiState.value = _uiState.value?.copy(directoresSeleccionados = selectedDirectores)

    }

    private fun isSelected(director: Director): Boolean {
        return selectedDirectores.contains(director)
    }

}
