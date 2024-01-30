package com.example.unapantallainesmr.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.unapantallainesmr.domain.modelo.Serie

import com.example.unapantallainesmr.ui.theme.UnaPantallaInesMRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoPantalla(
    viewModel: MainViewModel? = null,

    ) {
    UnaPantallaInesMRTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    CajaTexto(texto = "Formulario")
                }
                Spacer(modifier = Modifier.height(8.dp))
                val textoViewModel = viewModel?.uiState?.collectAsState()
                val context = currentCompositionLocalContext
                val idGlob = textoViewModel?.value?.id ?: 1
                Row {
                    val estadoModo = textoViewModel?.value?.editMode ?: false
                    if (estadoModo) {
                        Column {
                            Spacer(modifier = Modifier.height(15.dp))
                            TextField(value = textoViewModel?.value?.texto ?: "", onValueChange = {
                                viewModel?.handleEvent(MainEvent.ChangeTexto(it))
                            }, placeholder = { Text("Título") })
                            Spacer(modifier = Modifier.height(15.dp))
                            TextField(value = textoViewModel?.value?.descripcion ?: "", onValueChange = {
                                viewModel?.handleEvent(MainEvent.ChangeDescripcion(it))
                            }, placeholder = { Text("Descripcion") })
                        }
                    } else {
                        val tituloSerie = textoViewModel?.value?.serie?.titulo ?: "No carga"
                        val descripcionSerie =
                            textoViewModel?.value?.serie?.descripcion ?: "No carga"
                        Column {
                            CajaTexto(texto = tituloSerie)
                            Spacer(modifier = Modifier.height(8.dp))
                            CajaTexto(texto = descripcionSerie)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        var estadoModo = viewModel?.uiState?.value?.editMode
                        viewModel?.handleEvent(MainEvent.ChangeMode(estadoModo))
                    }) {
                        Text(text = "Editar")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        viewModel?.handleEvent(MainEvent.AvanzarId(idGlob))
                    }) {
                        Text(text = "Anterior")
                    }
                    Button(onClick = {
                        viewModel?.handleEvent(MainEvent.RetrocederId(idGlob))
                    }) {
                        Text(text = "Siguiente")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        val serieActualizada =
                            Serie(idGlob, textoViewModel?.value?.texto ?: "", textoViewModel?.value?.descripcion ?: "")
                        viewModel?.handleEvent(MainEvent.UpdateSerie(serieActualizada))
                    }) {
                        Text(text = "Actualizar")
                    }

                    Button(onClick = {
                        val nuevaSerie =
                            Serie(0, textoViewModel?.value?.texto ?: "", textoViewModel?.value?.descripcion ?: "")
                        viewModel?.handleEvent(MainEvent.AddSerie(nuevaSerie))
                    }) {
                        Text(text = "Añadir")
                    }

                    Button(onClick = {

                    }) {
                        Text(text = "Eliminar")
                    }
                }
            }
        }
    }
}

@Composable
fun CajaTexto(texto: String, modifier: Modifier = Modifier) {
    Row() {
        Text(
            text = texto,
            modifier = modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContenidoPantalla()
}