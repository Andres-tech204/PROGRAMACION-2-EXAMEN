package cl.andvasquez.android.examenprogramacion2.Datos

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import cl.andvasquez.android.examenprogramacion2.Datos.DB.BaseDatos
import cl.andvasquez.android.examenprogramacion2.Datos.DB.MedidasEntidad

class MViewModel(application: Application) : AndroidViewModel(application) {

    var allMedidas = mutableStateListOf<MedidasEntidad>()

    suspend fun registrarMedidas(medida: String, fecha: String, opcion: String, contexto: Context) {
        if (!medida.isNullOrBlank() && !fecha.isNullOrBlank() && !opcion.isNullOrBlank()){
            var reg = MedidasEntidad(medidor = medida.toInt(), fecha = fecha, tipo = opcion)
            allMedidas.add(reg)
            BaseDatos.getDatabase(contexto).medidasDao().insert(reg)
        }
    }

    suspend fun mostrarListado(contexto: Context) {
        allMedidas.clear()
        allMedidas.addAll(BaseDatos.getDatabase(contexto).medidasDao().getAllMediciones())
    }

    suspend fun borrarRegistro(contexto:Context, medidas: MedidasEntidad) {
        BaseDatos.getDatabase(contexto).medidasDao().borrarRegistro(medidas)
        mostrarListado(contexto)
    }
}