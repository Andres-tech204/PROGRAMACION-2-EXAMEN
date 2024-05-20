package cl.andvasquez.android.examenprogramacion2.Datos.Paginas

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.andvasquez.android.examenprogramacion2.Datos.MViewModel
import cl.andvasquez.android.examenprogramacion2.R
import cl.andvasquez.android.examenprogramacion2.ui.theme.btnColor
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Locale

@Preview
@Composable
fun FormularioMedidas(
    onBackClick:() -> Unit = {},
    vm: MViewModel = viewModel()
){

    val contexto = LocalContext.current
    var medidor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    val SelecOpcion = listOf(
        contexto.getString(R.string.iconoagua),
        contexto.getString(R.string.iconoluz),
        contexto.getString(R.string.iconogas)
    )
    val calendar = Calendar.getInstance()
    var datePickerDialogState by remember { mutableStateOf<DatePickerDialog?>(null) }
    var selectedoption by remember { mutableStateOf("") }
    val mainScope = MainScope()
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.icono_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = contexto.getString(R.string.Titulo_Formulario),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(50.dp))
                TextField(
                    value = medidor,
                    onValueChange = { medidor = it },
                    label = { Text(contexto.getString(R.string.input_medidas)) },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "calendario"
                        )
                    }
                )
                TextField(
                    value = fecha,
                    modifier = Modifier
                        .clickable { datePickerDialogState?.show() },
                    onValueChange = { fecha = it },
                    label = { Text(text = contexto.getString(R.string.input_fecha)) },
                    singleLine = true,
                    maxLines = 1,
                    enabled = false,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "calendario"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(60.dp))
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = contexto.getString(R.string.MedidorDe),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 60.dp)
                    )
                    SelecOpcion.forEach { opcion ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .selectable(
                                    selected = selectedoption == opcion,
                                    onClick = { selectedoption = opcion }
                                )
                                .padding(horizontal = 70.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedoption == opcion,
                                onClick = { selectedoption = opcion }
                            )
                            if (opcion == contexto.getString(R.string.iconoagua)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.iconoagua),
                                    contentDescription = "agua",
                                    modifier = Modifier.width(20.dp)
                                )
                            }
                            if (opcion == contexto.getString(R.string.iconoluz)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.iconoluz),
                                    contentDescription = "luz",
                                    modifier = Modifier.width(20.dp)
                                )
                            }
                            if (opcion == contexto.getString(R.string.iconogas)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.iconogas),
                                    contentDescription = "gas",
                                    modifier = Modifier.width(20.dp)
                                )
                            }
                            Text(text = opcion.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(1.dp))
                Button(
                    onClick = {
                        onBackClick(); mainScope.launch {
                        vm.registrarMedidas(medidor, fecha, selectedoption, contexto)
                    }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = btnColor)
                ) {
                    Text(text = contexto.getString(R.string.btn_registrar_medidas))
                }
            }
        }
        DisposableEffect(contexto) {
            datePickerDialogState = DatePickerDialog(
                contexto,
                { _, year, month, dayOfMonth ->
                    fecha = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            onDispose {
                datePickerDialogState?.dismiss()
            }
        }
    }
}

