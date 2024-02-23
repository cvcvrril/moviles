package com.example.aprobarines.ui.screens.listapersonaje


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprobarines.domain.modelo.Personaje
import kotlinx.coroutines.delay


@Composable
fun PantallaListaPersonajes(
    viewModel: PantallaListaPersonajeViewModel = hiltViewModel(),
    onViewDetalle: (Int) -> Unit,
    bottomNavigationBar: @Composable () -> Unit = {}


) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handleEvent(PantallaListaPersonajeEvent.GetPersonajes)
    }

    PantallaListaPersonajesInterna(
        state = state.value,
        onViewDetalle = onViewDetalle,
        onDelete = {viewModel.handleEvent(PantallaListaPersonajeEvent.DeletePersonaje(1))},
        bottomNavigationBar = bottomNavigationBar,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaPersonajesInterna(
    state: PantallaListaPersonajeState,
    onViewDetalle: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    bottomNavigationBar: @Composable () -> Unit = {},

    ) {

    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
        floatingActionButton = {
            Button(onClick = { /*TODO*/ }) {
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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

            items(items = state.personajes, key = { persona -> persona.id }) { personaje ->

                SwipeToDeleteContainer(
                    item = personaje,
                    onDelete = {
                        it.id?.let{it1 -> onDelete(it1)}
                    }
                ) { personaje ->
                    PersonajeItem(
                        personaje = personaje,
                        onViewDetalle = onViewDetalle,
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = tween(1000)
                        )
                    )
                }
            }
        }

    }


}


@Composable
fun PersonajeItem(
    personaje: Personaje,
    onViewDetalle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onViewDetalle(personaje.id) }) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.weight(weight = 0.4F),
                text = personaje.nombre
            )
            Text(
                modifier = Modifier.weight(0.4F),
                text = personaje.descripcion
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (Personaje) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val dismissState = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = dismissState,
            background = {
                DeleteBackground(swippeDismissValue = dismissState)
            },
            dismissContent = {
                content(item)
            },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swippeDismissValue: DismissState
) {
    val color = if (swippeDismissValue.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White,
        )

    }
}

@Preview
@Composable
fun previewVideojuegoItem() {
    PersonajeItem(
        personaje = Personaje(0, "Prueba Personaje", "Descripción Prueba Personaje"),
        onViewDetalle = {},
    )
}
