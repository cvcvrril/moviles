package com.example.unapantallainesmr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.unapantallainesmr.ui.theme.UnaPantallaInesMRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ContenidoPantalla()
            Pantalla()
        }
    }
}

@Composable
fun Pantalla(

    viewModel: MainViewModel = hiltViewModel()

) {
    ContenidoPantalla(viewModel)

}

@Composable
fun ContenidoPantalla(
    viewModel: MainViewModel? = null,

    ) {
    UnaPantallaInesMRTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    //.width(200.dp)
                    //.height(200.dp)
                    .padding(20.dp)                         /*Esto es para ajustar los márgenes generales*/
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    CajaTexto(texto = "Formulario")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    var textoViewModel = viewModel?.text?.collectAsState()
                   //TextoEditable(textoViewModel)          /*Esto para cuando arregle el método*/
                    TextField(value = textoViewModel?.value ?: "", onValueChange = {
                        viewModel?.changeText(it)
                    },  placeholder = { Text("Título") })
                }
            }
        }
    }
}

@Composable
fun CajaTexto(texto: String) {
    Row() {
        Text(text = texto)
    }
}

@Composable
fun TextoEditable() {

    //TODO: PASAR EL TEXTFIELD AQUÍ

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContenidoPantalla()
}