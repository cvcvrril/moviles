package com.example.aprobarines.ui.screens.registro

import androidx.lifecycle.ViewModel
import com.example.aprobarines.domain.usecases.DoRegistroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PantallaRegistroViewModel  @Inject constructor(
    private val doRegistroUseCase: DoRegistroUseCase,
): ViewModel(){

    private val _state = MutableStateFlow(PantallaRegistroState())
    val state : StateFlow<PantallaRegistroState> = _state.asStateFlow()




}