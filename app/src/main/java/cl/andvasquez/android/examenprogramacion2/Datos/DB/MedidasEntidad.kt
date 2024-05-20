package cl.andvasquez.android.examenprogramacion2.Datos.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medidas_table")
data class MedidasEntidad(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medidor: Int,
    val fecha: String,
    val tipo: String
)