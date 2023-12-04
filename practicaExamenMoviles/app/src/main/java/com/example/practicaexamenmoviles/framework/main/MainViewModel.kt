package com.example.practicaexamenmoviles.framework.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaexamenmoviles.domain.model.Videojuego
import com.example.practicaexamenmoviles.domain.usecases.videojuego.GetAllVideojuegosUseCase
import com.example.practicaexamenmoviles.utils.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllVideojuegosUseCase: GetAllVideojuegosUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private var selectedVideojuegos = mutableListOf<Videojuego>()

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    init{
        _uiState.value = MainState(
            error = null,
            viedojuegosSelected = selectedVideojuegos.toList(),
            selectedMode = false
        )
        getVideojuegos()

    }


    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetVideojuegos -> {
                getVideojuegos()
            }

            MainEvent.ErrorVisto -> _uiState.value = _uiState.value?.copy(error = null)

            is MainEvent.DeleteVideojuego -> {
                deleteVideojuego(event.videojuego)
            }

            is MainEvent.SeleccionaVdieojuegos -> seleccionaVideojuego(event.videojuego)
            is MainEvent.DeleteVideojuegosSeleccionados -> {
                deleteVideojuegos()
            }

            is MainEvent.GetVideojuegosFiltrados -> getVideojuegosFiltro(event.filtro)
            MainEvent.ResetSelectMode -> {
                resetSelectMode()
            }

            MainEvent.StartSelectedMode -> _uiState.value = _uiState.value?.copy(selectedMode = true)
        }
    }

    private fun getVideojuegos() {
        viewModelScope.launch {
            val result = getAllVideojuegosUseCase()
            Log.d("Videojuegos (MainViewModel1)", "Videojuegos: ${result}")
            when (result) {
                is NetworkResult.Error -> _error.value = result.message ?: "Ha habido un error"
                is NetworkResult.Success -> {
                    result.data?.let { videojuegos ->
                        _uiState.value = _uiState.value?.copy(videojuegos = videojuegos)
                    }
                }
            }
        }

    }

    private fun deleteVideojuegos() {
        TODO("Not yet implemented")
    }

    private fun deleteVideojuego(videojuego: Videojuego) {

    }

    private fun seleccionaVideojuego(videojuego: Videojuego) {

    }

    private fun getVideojuegosFiltro(filtro: String) {

    }

    private fun resetSelectMode() {
        TODO("Not yet implemented")
    }




}