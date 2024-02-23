package com.example.aprobarines.ui.screens.pantallaaddpersonaje

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aprobarines.ui.activities.ListaActivity
import com.example.aprobarines.ui.screens.login.PantallaLoginEvent
import kotlin.reflect.KFunction1

@Composable
fun PantallaAddPersonaje(
    navController: NavController,
    viewModel: PantallaAddPersonajeViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()

    PantallaAddPersonajeInterna(
        state = state.value,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun PantallaAddPersonajeInterna(
    state: PantallaAddPersonajeState,
    handleEvent: KFunction1<PantallaAddPersonajeEvent, Unit>,

    ){

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                .background(Color.Gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Nuevo personaje")
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                placeholder = { Text(text = "Nombre del personaje") },
                value = state.personaje?.nombre ?: "",
                onValueChange = { valueIntroduced ->
                    handleEvent(PantallaAddPersonajeEvent.IntroducedNombre(valueIntroduced))
                })
            Spacer(modifier = Modifier.height(30.dp))
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    handleEvent(PantallaAddPersonajeEvent.AddPersonaje("prueba"))
                }) {
                Text(text = "Añadir personaje")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }

    }


}