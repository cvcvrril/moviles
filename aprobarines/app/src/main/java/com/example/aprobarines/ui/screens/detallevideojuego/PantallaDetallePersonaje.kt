package com.example.composefullequip.ui.screens.detalle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetallePersonajeEvent
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetallePersonajeState
import com.example.aprobarines.ui.screens.detallevideojuego.PantallaDetallePersonajeViewModel

@Composable
fun PantallaDetallePersonaje(
    viewModel: PantallaDetallePersonajeViewModel = hiltViewModel(),
    personajeId: Int,
) {

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(PantallaDetallePersonajeEvent.GetPersonaje(personajeId))
    }

    PantallaDetallePersonajeInterna(
        state = state.value,
        id = personajeId,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun PantallaDetallePersonajeInterna(
    id: Int,
    state: PantallaDetallePersonajeState,
    handleEvent: (PantallaDetallePersonajeEvent) -> Unit
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
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(text = "Hola")
                Text("Pantalla Detalle Personaje ${id}")
                Text("Nombre : " + handleEvent(PantallaDetallePersonajeEvent.GetPersonaje(id)))
            }
        }

    }

}
