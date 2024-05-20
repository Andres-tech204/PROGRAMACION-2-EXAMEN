package cl.andvasquez.android.examenprogramacion2.Datos

import cl.andvasquez.android.examenprogramacion2.Datos.DB.MedidasDao
import cl.andvasquez.android.examenprogramacion2.Datos.DB.MedidasEntidad

class RepositorioMedidas(private val medidasDao: MedidasDao) {

    val allMediciones: List<MedidasEntidad> = medidasDao.getAllMediciones()

    suspend fun insert(medidas: MedidasEntidad) {
        medidasDao.insert(medidas)
    }
}
