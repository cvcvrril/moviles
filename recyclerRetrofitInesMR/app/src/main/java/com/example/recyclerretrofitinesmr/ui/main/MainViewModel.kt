    package com.example.recyclerretrofitinesmr.ui.main

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.recyclerretrofitinesmr.data.repository.DirectorRepository
    import com.example.recyclerretrofitinesmr.domain.Director
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableSharedFlow
    import kotlinx.coroutines.flow.asSharedFlow
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class MainViewModel @Inject constructor(private val directorRepository: DirectorRepository) :
        ViewModel() {

        private val listaDirectores = mutableListOf<Director>()
        private val _error = MutableLiveData<String>()
        val error: LiveData<String> get() = _error
        private val _sharedFlow = MutableSharedFlow<String>()
        val sharedFlow = _sharedFlow.asSharedFlow()
        private val _uiState = MutableLiveData(MainState())
        val uiState: LiveData<MainState> get() = _uiState

        private fun getDirectores() {
            viewModelScope.launch {
                val res = directorRepository.getDirector()

                _uiState.value = _uiState.value?.copy(directores = listaDirectores.toList())

            }
        }

        private fun getDirectores(filtro: String){
            viewModelScope.launch {
                _uiState.value = uiState.value?.copy(
                    directores = listaDirectores.filter {
                        it.nombre.startsWith(filtro)
                    }.toList()
                )
            }
        }

        private fun deleteDirector(directores: List<Director>) {

        }

    }
