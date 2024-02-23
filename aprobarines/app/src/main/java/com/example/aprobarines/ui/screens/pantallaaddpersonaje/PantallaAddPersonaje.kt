package com.example.aprobarines.ui.screens.pantallaaddpersonaje

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun PantallaAdd(
    navController: NavController,
    viewModel: PantallaAddPersonajeViewModel = hiltViewModel()
){
    val context = LocalContext.current
}