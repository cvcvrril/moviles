package com.example.aprobarines.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PantallaLogin(
    viewModel: PantallaLoginViewModel = hiltViewModel(),
    bottomNavigationBar : @Composable () -> Unit = {}
) {

    val state = viewModel.state.collectAsState()

    //TODO: Montar aquí todo el tema de la funcionalidad, métodos del ViewModel, etc.

    PantallaLoginInterna(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar
    )

}


fun PantallaLoginInterna(
    state: PantallaLoginState,
    bottomNavigationBar: @Composable () -> Unit = {}
){


}

@Preview
@Composable
fun previewPantallaLogin(){

}


