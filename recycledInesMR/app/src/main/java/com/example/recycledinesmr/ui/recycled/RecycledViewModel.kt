package com.example.recycledinesmr.ui.recycled

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycledinesmr.domain.usecases.GetListaUseCase
import com.example.recycledinesmr.ui.main.MainState

class RecycledViewModel (
    private val getListaUseCase: GetListaUseCase
) : ViewModel(){

    private val _uiState = MutableLiveData(MainState())

}