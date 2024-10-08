package com.example.spring6.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.spring6.component.topBar
import com.example.spring6.viewModel.telefonoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailView(navController: NavController, viewModel: telefonoViewModel, id: Int,name:String){
    val context = LocalContext.current
    var email = " info@novaera.cl"
    var asunto = "Consulta  $name - Id: ${id}"
    var mensaje = """Hola
        
        Me gustaría obtener más información del móvil ${name} de código {id}. Quedo atento.
        
        Gracias.
    """.trimIndent()
    Scaffold(
        topBar={topBar("Inicio", navController)},
        content = { ContentDetailView(it,navController, viewModel , id )},
        floatingActionButton = {FloatingActionButton(
            onClick = { val intent = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(email)
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje)
                    intent.type = "message/rfc822"
                    context.startActivity(Intent.createChooser(intent, "Email del cliente")) },
        ) {
            Icon(Icons.Filled.Email, contentDescription = "Add")
        }
        }

    )
}

@Composable
fun ContentDetailView(it: PaddingValues, navController: NavController, viewModel: telefonoViewModel, id:Int) {
    LaunchedEffect(Unit) {
        viewModel.getProductById(id)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }
    val telefono = viewModel.state.telefono
    val creditString = if (telefono.credito == true) "Acepta credito" else "Solo efectivo"
    Column(modifier = Modifier.padding(it).fillMaxSize().verticalScroll(rememberScrollState())) {
        Image(
            painter = rememberAsyncImagePainter(telefono.imagen),
            contentDescription = telefono.nombre,
            modifier = Modifier.fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.padding(2.dp).fillMaxWidth().height(200.dp)){
            Text("Nombre: "+ telefono.nombre, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Precio: $${telefono.precio}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Ultimo precio: $${telefono.ultimoPrecio}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Credito: $creditString", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Descripcion: ${telefono.descripcion}", style = MaterialTheme.typography.bodyMedium)
        }



    }
}

