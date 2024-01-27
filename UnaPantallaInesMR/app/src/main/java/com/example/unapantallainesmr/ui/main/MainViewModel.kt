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

    //IDEA: Montar en el MainEvent un GetEditModo para comprobar el estado (si se puede editar o no)

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState

    private fun changeText(texto: String) {
        _uiState.value = _uiState.value.copy(texto = texto)
    }

    private fun changeMode(mode: Boolean) {
        _uiState.value = _uiState.value.copy(editMode = mode)
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.Error -> _uiState.update { it.copy(error = null) }
            MainEvent.GetSeries -> getAllSeries()
            is MainEvent.ChangeTexto -> changeText(event.texto)
            is MainEvent.ChangeMode -> changeMode(event.mode)
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
                .collect{result->
                    _uiState.update {
                        it.copy(
                            serie = result,
                            error = "Serie cargada correctamente"
                        )
                    }
                }
        }
    }

}