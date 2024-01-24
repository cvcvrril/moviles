package com.example.unapantallainesmr.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unapantallainesmr.data.SerieRepository
import com.example.unapantallainesmr.domain.modelo.Serie
import com.example.unapantallainesmr.domain.usecases.GetAllSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SerieRepository,
    private val getAllSeriesUseCase: GetAllSeriesUseCase
) : ViewModel() {

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text.asStateFlow()
    private val _series: MutableStateFlow<Serie>? = null
    //val series: StateFlow<Serie> get() = _series

    fun changeText(texto: String) {
        _text.value = texto
    }

    private fun getAll(){
        viewModelScope.launch {
            try{
                //_series.value = getAllSeriesUseCase.invoke()


            }catch (e: Exception){

            }
        }

    }


}