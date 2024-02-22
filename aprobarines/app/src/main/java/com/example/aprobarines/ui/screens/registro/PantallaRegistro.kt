@file:JvmName("PantallaRegistroKt")

package com.example.aprobarines.ui.screens.registro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PantallaRegistro(
    viewModel: PantallaRegistroViewModel = hiltViewModel(),
    bottomNavigationBar : @Composable () -> Unit = {}
) {

    val state = viewModel.state.collectAsState()
    PantallaRegistroInterna(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar,
    )

}


@Composable
fun PantallaRegistroInterna(
    state: PantallaRegistroState,
    bottomNavigationBar: @Composable () -> Unit = {}
){

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
    ){ innerPadding->
        LaunchedEffect(state.error) {
            state.error?.let {
                snackbarHostState.showSnackbar(
                    message = state.error.toString(),
                    duration = SnackbarDuration.Short,
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Text("Registro")
        }

    }




}

@Preview
@Composable
fun previewPantallaRegistro(){

}


