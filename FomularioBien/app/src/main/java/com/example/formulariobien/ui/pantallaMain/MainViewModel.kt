package com.example.formulariobien.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.personas.AddPeliculasUseCase
import com.example.formulariobien.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addPeliculasUseCase: AddPeliculasUseCase,
) : ViewModel(){

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun addPelicula(pelicula: Pelicula){
        if (!addPeliculasUseCase(pelicula)){
            _uiState.value = MainState(
                pelicula = _uiState.value.let { pelicula },
                //error = stringProvider.getString(R.string.name),
                error = "error"
                )
            _uiState.value = _uiState
                .value?.copy(error = Constantes.ERROR)
        }
    }

}