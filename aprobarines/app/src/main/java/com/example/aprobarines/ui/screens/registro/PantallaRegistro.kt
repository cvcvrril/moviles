package com.example.aprobarines.ui.screens.registro

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aprobarines.domain.modelo.User
import kotlin.reflect.KFunction1

@Composable
fun PantallaRegistro(
    viewModel: PantallaRegistroViewModel = hiltViewModel(),
    bottomNavigationBar: @Composable () -> Unit = {}
) {

    val state = viewModel.state.collectAsState()

    PantallaRegistroInterna(
        state = state.value,
        handleEvent = viewModel::handleEvent,
        bottomNavigationBar = bottomNavigationBar,
    )

}


@Composable
fun PantallaRegistroInterna(
    state: PantallaRegistroState,
    handleEvent: KFunction1<PantallaRegistroEvent, Unit>,
    bottomNavigationBar: @Composable () -> Unit = {}
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Text("Registro")
            TextField(
                placeholder = { Text(text = "Username") },
                value = state.user?.username ?: "",
                onValueChange = { valueIntroduced ->
                    handleEvent(PantallaRegistroEvent.IntroducedUsername(valueIntroduced))
                })
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                placeholder = { Text(text = "Password") },
                value = state.user?.password ?: "",
                onValueChange = { valueIntroduced ->
                    handleEvent(PantallaRegistroEvent.IntroducedPassword(valueIntroduced))
                })
            
            Button(onClick = {
                handleEvent(PantallaRegistroEvent.DoRegister(User("prueba", "prueba", "USER")))
            }) {
                
            }
        }

    }


}

@Preview
@Composable
fun previewPantallaRegistro() {

}


