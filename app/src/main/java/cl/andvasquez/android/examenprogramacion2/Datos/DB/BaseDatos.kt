package cl.andvasquez.android.examenprogramacion2.Datos.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MedidasEntidad::class], version = 1, exportSchema = false)
abstract class BaseDatos : RoomDatabase() {

    abstract fun medidasDao(): MedidasDao

    companion object {
        @Volatile
        private var INSTANCE: BaseDatos? = null

        fun getDatabase(context: Context): BaseDatos {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "medidas_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
