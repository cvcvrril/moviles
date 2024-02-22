@file:JvmName("PantallaRegistroKt")

package com.example.aprobarines.ui.screens.registro

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PantallaRegistro(
    viewModel: PantallaRegistroViewModel = hiltViewModel(),
    bottomNavigationBar : @Composable () -> Unit = {}
) {

    val state = viewModel.state.collectAsState()

    //TODO: Montar aquí todo el tema de la funcionalidad, métodos del ViewModel, etc.

    PantallaRegistroInterna(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar
    )

}


@Composable
fun PantallaRegistroInterna(
    state: PantallaRegistroState,
    bottomNavigationBar: @Composable () -> Unit = {}
){

    Text("Registro")

}

@Preview
@Composable
fun previewPantallaRegistro(){

}


