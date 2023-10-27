package com.example.recycledinesmr.ui.recycled

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recycledinesmr.domain.usecases.GetListaUseCase
import com.example.recycledinesmr.ui.Constantes
import com.example.recycledinesmr.ui.main.MainState
import java.lang.IllegalArgumentException

class RecycledViewModel (
    private val getListaUseCase: GetListaUseCase
) : ViewModel(){

    private val _uiState = MutableLiveData(MainState())

}

class RecycledViewModelFactory(
    private val getListaUseCase: GetListaUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecycledViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RecycledViewModel(
                getListaUseCase
            ) as  T
        }
        throw IllegalArgumentException(Constantes.UNKNOWN)
    }


}