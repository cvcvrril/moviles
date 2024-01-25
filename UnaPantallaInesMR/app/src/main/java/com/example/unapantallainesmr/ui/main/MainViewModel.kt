package com.example.unapantallainesmr.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unapantallainesmr.data.SerieRepository
import com.example.unapantallainesmr.domain.modelo.Serie
import com.example.unapantallainesmr.domain.usecases.GetAllSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SerieRepository,
    private val getAllSeriesUseCase: GetAllSeriesUseCase
) : ViewModel() {

    //INFO: Sé que esto lo tengo mal, pero no sé cómo hacerlo bien

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text.asStateFlow()

    fun changeText(texto: String) {
        _text.value = texto
    }

    fun handleEvent(event:MainEvent){
        when(event){
            MainEvent.Error -> _uiState.update { it.copy(error = null) }
            MainEvent.GetSeries -> getAll()
        }
    }

    private fun getAll(){
        viewModelScope.launch {
            try{
                repository.getAll()
                
            }catch (e: Exception){
                throw e
            }
        }

    }


}