package com.example.composefullequip.ui.screens.lista


import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprobarines.domain.modelo.Videojuego



@Composable
fun PantallaListaVideojuegos(
    viewModel: PantallaListaVideojuegoViewModel = hiltViewModel(),
    onViewDetalle: (Int) -> Unit,
    bottomNavigationBar : @Composable () -> Unit = {}


    ) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(PantallaListaVideojuegoEvent.GetVideojuegos)
    }

    PantallaListaVideojuegosInterna(
        state = state.value,
        onViewDetalle = onViewDetalle,
        bottomNavigationBar = bottomNavigationBar,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaListaVideojuegosInterna(
    state: PantallaListaVideojuegoState,
    onViewDetalle: (Int) -> Unit,
    bottomNavigationBar : @Composable () -> Unit = {},

    ) {

    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
    ) { innerPadding ->
        LaunchedEffect(state.error) {
            state.error?.let {
                snackbarHostState.showSnackbar(
                    message = state.error.toString(),
                    duration = SnackbarDuration.Short,
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
        ) {

            items(items = state.videojuegos, key = { persona -> persona.id }) {
                    videojuego ->
                VideojuegoItem(videojuego = videojuego,
                    onViewDetalle = onViewDetalle,
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(1000)
                    ))
            }
        }

    }


}


@Composable
fun VideojuegoItem(videojuego: Videojuego,
                   onViewDetalle: (Int) -> Unit,
                   modifier: Modifier = Modifier){

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onViewDetalle(videojuego.id) } ) {
        Row( modifier = Modifier.padding(8.dp)){
            Text(
                modifier = Modifier.weight(weight = 0.4F),
                text = videojuego.titulo
            )
            Text(
                modifier = Modifier.weight(0.4F),
                text = videojuego.descripcion)
        }
    }

}


@Preview
@Composable
fun previewVideojuegoItem() {
    VideojuegoItem(
        videojuego = Videojuego(0, "Prueba Videojuego", "Descripción Prueba Videojuego"),
        onViewDetalle = {},
    )
}
