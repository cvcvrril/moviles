package com.example.composefullequip.ui.screens.detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetalleVideojuegoEvent
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetalleVideojuegoState
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetalleVideojuegoViewModel

@Composable
fun PantallaDetalleVideojuego(
    viewModel: PantallaDetalleVideojuegoViewModel = hiltViewModel(),
    videojuegoId: Int
) {

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(PantallaDetalleVideojuegoEvent.GetVideojuego(videojuegoId))
    }

    PantallaDetalleVideojuegoInterna(
        id = videojuegoId,
        state = state.value,
    )

}

@Composable
fun PantallaDetalleVideojuegoInterna(
    id: Int,
    state: PantallaDetalleVideojuegoState,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
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
                .background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val videojuegoSel = state.videojuego
            Text(text = "Detalle personaje")
            Spacer(modifier = Modifier.height(30.dp))
            Text("Id : ${id}")
            Spacer(modifier = Modifier.height(30.dp))
            Text("Nombre : " + videojuegoSel?.titulo)
            Spacer(modifier = Modifier.height(30.dp))
            Text("Descripción : ")
            Spacer(modifier = Modifier.height(10.dp))
            Text(videojuegoSel?.descripcion ?: "")


        }

    }

}