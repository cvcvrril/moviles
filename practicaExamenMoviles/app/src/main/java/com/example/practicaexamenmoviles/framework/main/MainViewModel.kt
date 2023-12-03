package com.example.practicaexamenmoviles.framework.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaexamenmoviles.domain.model.Videojuego
import com.example.practicaexamenmoviles.domain.usecases.videojuego.GetAllVideojuegosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun getVideojuegos() {
        TODO("Not yet implemented")
    }


}