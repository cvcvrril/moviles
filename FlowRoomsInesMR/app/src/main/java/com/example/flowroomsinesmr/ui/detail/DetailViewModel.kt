package com.example.flowroomsinesmr.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowroomsinesmr.domain.usecases.credencial.GetAllVideojuegosUseCase
import com.example.flowroomsinesmr.utils.InternetConexion
import com.example.flowroomsinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getAllVideojuegosUseCase: GetAllVideojuegosUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailContract.DetailState> by lazy {
        MutableStateFlow(DetailContract.DetailState())
    }
    val uiState: StateFlow<DetailContract.DetailState> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    init {
        _uiState.value = DetailContract.DetailState(
            error = null,
        )
    }

    fun handleEvent(event: DetailContract.DetailEvent) {
        when (event) {
            DetailContract.DetailEvent.ErrorVisto -> {
                _uiState.update { it.copy(error = null) }
            }

            DetailContract.DetailEvent.GetVideojuegos -> getVideojuegos()
        }
    }


    private fun getVideojuegos() {
        viewModelScope.launch {
            if (InternetConexion.hasInternetConnection(context = appContext)) {
                getAllVideojuegosUseCase.invoke()
                    .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                            }

                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    videojuegos = result.data ?: emptyList(), isLoading = false
                                )
                            }
                        }

                    }
            } else {
                _uiState.update {
                    it.copy(
                        error = "No hay internet",
                        isLoading = false
                    )
                }
            }
        }
    }


}