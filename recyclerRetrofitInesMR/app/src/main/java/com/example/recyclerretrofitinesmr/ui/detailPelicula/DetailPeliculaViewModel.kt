package com.example.recyclerretrofitinesmr.ui.detailPelicula

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerretrofitinesmr.domain.Pelicula
import com.example.recyclerretrofitinesmr.ui.detailDirector.DetailDirectorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPeliculaViewModel@Inject constructor(

): ViewModel() {

    private val _error = MutableLiveData<String>()
    private val _uiState = MutableLiveData(DetailPeliculaState())
    val uiState: LiveData<DetailPeliculaState> get() = _uiState
    val error: LiveData<String> get() = _error

    fun loadPeliculaDetails(pelicula: Pelicula) {
        _uiState.value = _uiState.value?.copy(pelicula = pelicula)
    }

}