package com.example.formulariobien.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.peliculas.AddPeliculasUseCase
import com.example.formulariobien.domain.usecases.peliculas.GetPeliculaUseCase
import java.lang.IllegalArgumentException

class MainViewModel(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val getPeliculaUseCase: GetPeliculaUseCase
) : ViewModel() {


    private var indiceActual = 0;

    private val _uiState = MutableLiveData<MainState>(MainState())
    val uiState: LiveData<MainState> get() = _uiState
    init {
        getPelicula(indiceActual)
    }

    /*Añadir película*/
    fun addPelicula(pelicula: Pelicula) {
        if (!addPeliculasUseCase(pelicula)) {
            _uiState.value = _uiState
                .value?.copy(error = Constantes.ERROR)
        }
    }

    /*Sacar película (id)*/
    fun getPelicula(id:Int){
        val pelicula = getPeliculaUseCase(id)
        if (pelicula!=null){
            _uiState.value = _uiState.value?.copy(pelicula = pelicula)

        } else
            _uiState.value = _uiState.value?.copy(error = "No hay peliculas disponibles")
    }

    /*Mostrar error*/
    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    fun avanzarPelicula(){
        if (indiceActual < Repository.getPelicula().size - 1) {
            indiceActual++
            getPelicula(indiceActual)
        } else {
            _uiState.value = _uiState.value?.copy(error = "¡Has llegado al final de la lista!")
        }
    }

    fun retrocederPelicula(){
        if (indiceActual > 0) {
            indiceActual--
            getPelicula(indiceActual)
        } else {
            _uiState.value = _uiState.value?.copy(error = "¡Has llegado al principio de la lista!")
        }
    }

}

class MainViewModelFactory(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val getPeliculaUseCase: GetPeliculaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addPeliculasUseCase,
                getPeliculaUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}