package cl.andvasquez.android.examenprogramacion2.Datos.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedidasDao {

    @Query("SELECT * FROM medidas_table")
    fun getAllMediciones():List<MedidasEntidad>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(medicion: MedidasEntidad)

    @Delete
    suspend fun borrarRegistro(med: MedidasEntidad)
}