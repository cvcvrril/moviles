package com.example.recyclerretrofitinesmr.ui.detailDirector

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerretrofitinesmr.domain.Director
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.domain.usecases.peliculas.GetAllPeliculasIdDirectorUseCase
import com.example.recyclerretrofitinesmr.domain.usecases.peliculas.GetAllPeliculasUseCase
import com.example.recyclerretrofitinesmr.ui.main.MainState
import com.example.recyclerretrofitinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailDirectorViewModel @Inject constructor(
    private val getAllPeliculasUseCase: GetAllPeliculasUseCase,
    private val getAllPeliculasIdDirectorUseCase: GetAllPeliculasIdDirectorUseCase
) : ViewModel() {

    private val listaPeliculas = mutableListOf<Pelicula>()
    private val _error = MutableLiveData<String>()
    private val _uiState = MutableLiveData(DetailDirectorState())
    val uiState: LiveData<DetailDirectorState> get() = _uiState
    val error: LiveData<String> get() = _error

    fun handleEvent(event: DetailDirectorEvent) {
        when (event) {
            is DetailDirectorEvent.GetAllPeliculasIdDirector -> {
                getAllPeliculasIdDirector(event.idDirector)
            }
        }

    }

    private fun getAllPeliculas() {
        viewModelScope.launch {
            val result = getAllPeliculasUseCase.getAllPeliculas()

            when (result) {
                is NetworkResult.Success -> {
                    listaPeliculas.clear()
                    listaPeliculas.addAll(result.data as List<Pelicula>)
                    _uiState.value = _uiState.value?.copy(peliculas = listaPeliculas)
                }

                is NetworkResult.Error -> {
                    _error.value = result.message.toString()
                }

                is NetworkResult.Loading -> TODO()
            }
        }
    }

    private fun getAllPeliculasIdDirector(idDirector: Int) {
        viewModelScope.launch {
            val result = getAllPeliculasIdDirectorUseCase.getAllPeliculasIdDirector(idDirector)
            Log.d("Peliculas (MainViewModel1)", "Peliculas: ${result}")
            when (result) {
                is NetworkResult.Success -> {
                    listaPeliculas.clear()
                    listaPeliculas.addAll(result.data as List<Pelicula>)
                    _uiState.value = _uiState.value?.copy(peliculas = listaPeliculas)
                }

                is NetworkResult.Error -> {
                    _error.value = result.message.toString()
                    Timber.e("Error en getAllPeliculasIdDirector: ${result.message}")
                }

                is NetworkResult.Loading -> TODO()
            }
        }


    }
}