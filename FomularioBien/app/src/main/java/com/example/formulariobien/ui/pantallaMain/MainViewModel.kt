package com.example.formulariobien.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.formulariobien.data.Repository
import com.example.formulariobien.domain.modelo.Pelicula
import com.example.formulariobien.domain.usecases.peliculas.AddPeliculasUseCase
import com.example.formulariobien.domain.usecases.peliculas.DeletePeliculaUseCase
import com.example.formulariobien.domain.usecases.peliculas.GetPeliculaUseCase
import com.example.formulariobien.domain.usecases.peliculas.UpdatePeliculasUseCase
import java.lang.IllegalArgumentException


class MainViewModel(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val getPeliculaUseCase: GetPeliculaUseCase,
    private val deletePeliculaUseCase: DeletePeliculaUseCase,
    private val updatePeliculaUseCase: UpdatePeliculasUseCase
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
    fun getPelicula(id: Int) {
        val pelicula = getPeliculaUseCase(id)
        if (pelicula != null) {
            _uiState.value = _uiState.value?.copy(pelicula = pelicula)

        } else
            _uiState.value = _uiState.value?.copy(error = Constantes.NO_HAY_PELICULAS_DISPONIBLES)
    }

    /*Mostrar error*/
    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    fun avanzarPelicula() {
        if (indiceActual < Repository.getPelicula().size - 1) {
            indiceActual++
            getPelicula(indiceActual)
            _uiState.value = _uiState.value?.copy(error = null, indiceActual = indiceActual)
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.FINAL_LISTA)
        }
    }

    fun retrocederPelicula() {
        if (indiceActual > 0) {
            indiceActual--
            getPelicula(indiceActual)
            _uiState.value = _uiState.value?.copy(error = null, indiceActual = indiceActual)
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.PRINCIPIO_LISTA)
        }
    }

    fun eliminarPelicula() {
        val pelicula = getPeliculaUseCase(indiceActual)
        if (pelicula != null) {
            if (deletePeliculaUseCase(indiceActual)) {
                _uiState.value = _uiState.value?.copy(error = null, pelicula = pelicula)
                if (indiceActual > 0) {
                    indiceActual--
                }
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR_ELIMINAR)
            }
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.NO_PELIS_ELIMINAR)
        }

    }

    fun actualizarPelicula(nuevaPelicula: Pelicula) {
        val peliculaActual = getPeliculaUseCase(indiceActual)
        if (peliculaActual != null) {
            if (updatePeliculaUseCase(indiceActual, nuevaPelicula)) {
                _uiState.value = _uiState.value?.copy(error = null)
                _uiState.value = _uiState.value?.copy(pelicula = nuevaPelicula)
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR_ACTUALIZAR)
            }
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.NO_PELIS_ACTUALIZAR)
        }
    }
}




class MainViewModelFactory(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val getPeliculaUseCase: GetPeliculaUseCase,
    private val deletePeliculaUseCase: DeletePeliculaUseCase,
    private val updatePeliculaUseCase: UpdatePeliculasUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addPeliculasUseCase,
                getPeliculaUseCase,
                deletePeliculaUseCase,
                updatePeliculaUseCase
            ) as T
        }
        throw IllegalArgumentException(Constantes.UNKNOWN)
    }

}