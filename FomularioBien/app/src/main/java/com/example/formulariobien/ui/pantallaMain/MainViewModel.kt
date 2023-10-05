package com.example.formulariobien.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formulariobien.domain.usecases.personas.AddPeliculasUseCase
import com.example.formulariobien.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addPeliculasUseCase: AddPeliculasUseCase
) : ViewModel(){
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

}