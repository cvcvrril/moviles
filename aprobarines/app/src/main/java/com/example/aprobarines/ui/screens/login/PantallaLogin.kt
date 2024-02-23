package com.example.aprobarines.ui.screens.login

import android.content.Context
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aprobarines.ui.activities.ListaActivity
import dagger.hilt.android.qualifiers.ActivityContext
import kotlin.reflect.KFunction1


@Composable
fun PantallaLogin(
    navController: NavController,
    viewModel: PantallaLoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state = viewModel.state.collectAsState()

    PantallaLoginInterna(
        context = context,
        navController = navController,
        state = state.value,
        handleEvent = viewModel::handleEvent,
    )

}


@Composable
fun PantallaLoginInterna(
    context: Context,
    navController: NavController,
    state: PantallaLoginState,
    handleEvent: KFunction1<PantallaLoginEvent, Unit>,
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
            Text(text = "Login")
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                placeholder = { Text(text = "Username") },
                value = state.user?.username ?: "",
                onValueChange = { valueIntroduced ->
                    handleEvent(PantallaLoginEvent.IntroducedUsername(valueIntroduced))
                })
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                placeholder = { Text(text = "Password") },
                value = state.user?.password ?: "",
                onValueChange = { valueIntroduced ->
                    handleEvent(PantallaLoginEvent.IntroducedPassword(valueIntroduced))
                })
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    //TODO: pasar bien los argumentos, dejo esto así para probar
                    handleEvent(PantallaLoginEvent.doLogin("ines", "ines"))
                    val intent = Intent(context, ListaActivity::class.java)
                    context.startActivity(intent)
                }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text("¿No tienes una cuenta?")
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate("registro")
                }) {
                Text(text = "Ir al Registro")
            }
        }

    }

}

@Preview
@Composable
fun previewPantallaLogin(){

}


