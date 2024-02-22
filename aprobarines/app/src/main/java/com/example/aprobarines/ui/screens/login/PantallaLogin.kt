package com.example.aprobarines.ui.screens.login

import androidx.compose.material3.Text
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

    PantallaLoginInterna(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar
    )

}


@Composable
fun PantallaLoginInterna(
    state: PantallaLoginState,
    bottomNavigationBar: @Composable () -> Unit = {}
){

    Text("Login")

}

@Preview
@Composable
fun previewPantallaLogin(){

}


