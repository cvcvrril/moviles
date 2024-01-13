package com.example.flowroomsinesmr.ui.detail.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowroomsinesmr.data.repository.VideojuegoRepository
import com.example.flowroomsinesmr.domain.modelo.Videojuego
import com.example.flowroomsinesmr.domain.usecases.credencial.GetAllVideojuegosUseCase
import com.example.flowroomsinesmr.ui.detail.DetailContract
import com.example.flowroomsinesmr.utils.InternetConexion
import com.example.flowroomsinesmr.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVideojuegoViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val videojuegoRepository: VideojuegoRepository,
    private val getAllVideojuegosUseCase: GetAllVideojuegosUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailContract.DetailVideojuegoState> by lazy {
        MutableStateFlow(DetailContract.DetailVideojuegoState())
    }
    val uiState: StateFlow<DetailContract.DetailVideojuegoState> = _uiState
    private var listaVideojuegos: MutableList<Videojuego>? = null

    init {
        _uiState.value = DetailContract.DetailVideojuegoState(
            error = null,
        )
    }

    fun handleEvent(event: DetailContract.DetailVideojuegoEvent) {
        when (event) {
            DetailContract.DetailVideojuegoEvent.ErrorVisto -> {
                _uiState.update { it.copy(error = null) }
            }

            DetailContract.DetailVideojuegoEvent.GetVideojuegos -> getVideojuegos()
        }
    }


    private fun getVideojuegos() {
        viewModelScope.launch {
            if (InternetConexion.hasInternetConnection(context = appContext)) {
                videojuegoRepository.getAllVideojuegos()
                    .catch(action = { cause ->
                        _uiState.update {
                            it.copy(error = cause.message)
                        }
                    }).collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                            }

                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = false) }

                            //NOTA: CUANDO PASA AL SUCCESS SE QUEDA EL ISLOADING A TRUE -> REVISAR SI HAY ALGÚN ERROR AQUÍ

                            is NetworkResult.Success ->{
                                _uiState.update {
                                    it.copy(
                                        videojuegos = result.data ?: emptyList(), isLoading = false
                                    )
                                }
                                listaVideojuegos = result.data?.toMutableList()
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
                when (val result = videojuegoRepository.getAllVideojuegosCacheo()) {
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Loading -> TODO()
                    is NetworkResult.Success -> _uiState.update {
                        it.copy(
                            videojuegos = result.data ?: emptyList(), isLoading = false
                        )
                    }
                }
            }
        }
    }


}