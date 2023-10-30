package com.example.recycledinesmr.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.domain.usecases.AddPeliculasUseCase
import com.example.recycledinesmr.domain.usecases.DeletePeliculaUseCase
import com.example.recycledinesmr.domain.usecases.UpdatePeliculasUseCase
import com.example.recycledinesmr.ui.Constantes
import java.lang.IllegalArgumentException




class MainViewModel(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val deletePeliculaUseCase: DeletePeliculaUseCase,
    private val updatePeliculaUseCase: UpdatePeliculasUseCase
) : ViewModel() {

    private var indiceActual = 0;

    private val _uiState = MutableLiveData<DetailState>(DetailState())
    val uiState: LiveData<DetailState> get() = _uiState

    init {
        _uiState.value = DetailState()
    }

    fun addPelicula(pelicula: Pelicula?) {
        if (pelicula != null) {
            addPeliculasUseCase(pelicula)
            _uiState.value = _uiState.value?.copy(error = Constantes.PELI_ANADIDA)
        }
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

    fun eliminarPelicula() {
        uiState.value?.let { deletePeliculaUseCase(it.pelicula) }
        _uiState.value = _uiState.value?.copy(error = Constantes.PELI_ELIMINADA)

    }

    fun actualizarPelicula(nuevaPelicula: Pelicula) {
        _uiState.value?.let { updatePeliculaUseCase(nuevaPelicula, it.pelicula) }
        _uiState.value = _uiState.value?.copy(pelicula = nuevaPelicula, error = Constantes.PELI_ACTUALIZADA)
    }
}


class MainViewModelFactory(
    private val addPeliculasUseCase: AddPeliculasUseCase,
    private val deletePeliculaUseCase: DeletePeliculaUseCase,
    private val updatePeliculaUseCase: UpdatePeliculasUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addPeliculasUseCase,
                deletePeliculaUseCase,
                updatePeliculaUseCase
            ) as T
        }
        throw IllegalArgumentException(Constantes.UNKNOWN)
    }
}