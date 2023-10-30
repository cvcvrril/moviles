package com.example.recycledinesmr.ui.recycled

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recycledinesmr.domain.modelo.Pelicula
import com.example.recycledinesmr.domain.usecases.GetListaUseCase
import com.example.recycledinesmr.ui.Constantes
import java.lang.IllegalArgumentException



class RecycledViewModel (
    private val getListaUseCase: GetListaUseCase
) : ViewModel(){

    private val _uiState = MutableLiveData<RecyclerState>()
    val uiState: LiveData<RecyclerState> get() = _uiState

    init {
        val listaPeliculas = getListaUseCase()
        if (listaPeliculas.isEmpty()){
            _uiState.value = RecyclerState(lista = emptyList(), error = Constantes.LISTA_VACIA)
        } else{
            _uiState.value = RecyclerState(lista = listaPeliculas, error = null)
        }
    }

    fun errorMostrado(){
        _uiState.value = _uiState.value?.copy(error = null)
    }

    fun getListaPeliculas(): List<Pelicula> {
        return getListaUseCase()
    }
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