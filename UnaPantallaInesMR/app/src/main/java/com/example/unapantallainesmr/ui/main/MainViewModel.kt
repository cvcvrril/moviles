package com.example.unapantallainesmr.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unapantallainesmr.data.SerieRepository
import com.example.unapantallainesmr.domain.modelo.Serie
import com.example.unapantallainesmr.domain.usecases.DeleteSerieUseCase
import com.example.unapantallainesmr.domain.usecases.GetAllSeriesUseCase
import com.example.unapantallainesmr.domain.usecases.GetSerieUseCase
import com.example.unapantallainesmr.domain.usecases.InsertSerieUseCase
import com.example.unapantallainesmr.domain.usecases.UpdateSerieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllSeriesUseCase: GetAllSeriesUseCase,
    private val getSerieUseCase: GetSerieUseCase,
    private val insertSerieUseCase: InsertSerieUseCase,
    private val updateSerieUseCase: UpdateSerieUseCase,
    private val deleteSerieUseCase: DeleteSerieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState

    private fun changeText(texto: String) {
        _uiState.value = _uiState.value.copy(texto = texto)
    }
    private fun changeDescripcion(descripcion: String) {
        _uiState.value = _uiState.value.copy(descripcion = descripcion)
    }

    private fun changeFavorito(favorito: Boolean){
        var nuevoFavorito = favorito ?: false
        nuevoFavorito = !nuevoFavorito
        _uiState.value = _uiState.value.copy(favorito = nuevoFavorito)
    }

    private fun changeMode(mode: Boolean?) {
        var nuevoMode = mode ?: false
        nuevoMode = !nuevoMode
        _uiState.value = _uiState.value.copy(editMode = nuevoMode)
    }

    private fun avanzarId(id: Int){
        var nuevoId = id
        nuevoId = nuevoId++
        getSerie(nuevoId)
        _uiState.value = _uiState.value.copy(id = nuevoId)
    }

    private fun retrocederId(id: Int){
        var nuevoId = id
        nuevoId = nuevoId--
        if (nuevoId < 0){
            nuevoId = 1
        }
        _uiState.value = _uiState.value.copy(id = nuevoId)
    }

    init {
        getAllSeries()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.Error -> _uiState.update { it.copy(error = null) }
            MainEvent.GetSeries -> getAllSeries()
            is MainEvent.ChangeTexto -> changeText(event.texto)
            is MainEvent.ChangeDescripcion -> changeDescripcion(event.descripcion)
            is MainEvent.ChangeFavorito -> changeFavorito(event.favorito)
            is MainEvent.ChangeMode -> changeMode(event.mode)
            is MainEvent.GetSerie -> getSerie(event.id)
            is MainEvent.AddSerie -> addSerie(event.serie)
            is MainEvent.DeleteSerie -> updateSerie(event.serie)
            is MainEvent.UpdateSerie -> deleteSerie(event.serie)
            is MainEvent.AvanzarId -> avanzarId(event.id)
            is MainEvent.RetrocederId -> retrocederId(event.id)

        }
    }

    private fun getAllSeries() {
        viewModelScope.launch {
            getAllSeriesUseCase.invoke()
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            series = result,
                            error = "Series cargadas correctamente",
                        )
                    }
                }

        }
    }

    private fun getSerie(id: Int) {
        viewModelScope.launch {
            getSerieUseCase.invoke(id)
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            serie = result,
                            error = "Serie cargada correctamente"
                        )
                    }
                }
        }
    }

    private fun addSerie(serie: Serie) {
        viewModelScope.launch {
            insertSerieUseCase.invoke(serie)
                .collect {
                    _uiState.update {
                        it.copy(
                            serie = serie,
                            error = "Serie añadida correctamente"
                        )
                    }
                }
        }
    }

    private fun updateSerie(serie: Serie) {
        viewModelScope.launch {
            updateSerieUseCase.invoke(serie)
                .collect {
                    _uiState.update {
                        it.copy(
                            serie = serie,
                            error = "Serie editada correctamente"
                        )
                    }
                }
        }
    }

    private fun deleteSerie(serie: Serie) {
        viewModelScope.launch {
            deleteSerieUseCase.invoke(serie)
                .collect{
                    _uiState.update {
                        it.copy(
                            error = "Serie eliminada correctamente"
                        )
                    }
                }


        }
    }


}