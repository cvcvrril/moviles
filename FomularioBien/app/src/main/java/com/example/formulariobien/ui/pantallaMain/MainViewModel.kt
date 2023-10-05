package com.example.formulariobien.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.personas.AddPeliculasUseCase
import com.example.formulariobien.utils.StringProvider
import java.lang.IllegalArgumentException

class MainViewModel(
    private val addPeliculasUseCase: AddPeliculasUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun addPelicula(pelicula: Pelicula) {
        if (!addPeliculasUseCase(pelicula)) {
            _uiState.value = MainState(
                pelicula = _uiState.value.let { pelicula },
                error = "error"
            )
            _uiState.value = _uiState
                .value?.copy(error = Constantes.ERROR)
        }
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

}

class MainViewModelFactory(
    private val addPeliculasUseCase: AddPeliculasUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addPeliculasUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}