package cl.andvasquez.android.examenprogramacion2.Datos.Paginas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.andvasquez.android.examenprogramacion2.Datos.MViewModel
import cl.andvasquez.android.examenprogramacion2.R
import cl.andvasquez.android.examenprogramacion2.ui.theme.btnColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ListaMedidas(
    onClick: () -> Unit = {},
    vm: MViewModel = viewModel(),


    ) {
    val contexto = LocalContext.current
    val mainScope = MainScope()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            vm.mostrarListado(contexto)
        }
    }

    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.icono_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = contexto.getString(R.string.titulo_inicio),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            items(vm.allMedidas) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        if (it.tipo == contexto.getString(R.string.iconoagua)) {
                            Icon(
                                painter = painterResource(id = R.drawable.iconoagua),
                                contentDescription = "imagenes representativas agua",
                                modifier = Modifier.width(20.dp)
                            )
                        }
                        if (it.tipo == contexto.getString(R.string.iconoluz)) {
                            Icon(
                                painter = painterResource(id = R.drawable.iconoluz),
                                contentDescription = "imagenes representativas luz",
                                modifier = Modifier.width(20.dp)
                            )
                        }
                        if (it.tipo == contexto.getString(R.string.iconogas)) {
                            Icon(
                                painter = painterResource(id = R.drawable.iconogas),
                                contentDescription = "imagenes representativas gas",
                                modifier = Modifier.width(20.dp)
                            )
                        }

                        Text(it.tipo.uppercase())
                        Text(it.medidor.toString())
                        Text(it.fecha)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                mainScope.launch { vm.borrarRegistro(contexto, it) }
                            },
                            modifier = Modifier.size(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.basura),
                                contentDescription = "Borrar registros"
                            )
                        }
                    }
                }
            }
        }
        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = btnColor),
                modifier = Modifier
                    .width(160.dp)
                    .height(35.dp)
            ) {
                Text(contexto.getString(R.string.btn_registrar_medidas))
            }
        }
    }
}