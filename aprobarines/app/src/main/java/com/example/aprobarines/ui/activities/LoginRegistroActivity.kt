package com.example.aprobarines.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.aprobarines.ui.navigation.LoginNavigation
import com.example.aprobarines.ui.screens.splashscreen.SplashScreenViewModel
import com.example.aprobarines.ui.theme.AprobarInesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginRegistroActivity : ComponentActivity(){

    private val viewModel by viewModels<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !viewModel.isReady.value
            }
        }

        setContent {
            AprobarInesTheme{
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    LoginNavigation()
                }
            }
        }
    }

}