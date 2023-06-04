package ibu.edu.unitask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ibu.edu.unitask.data.converters.DateConverter
import ibu.edu.unitask.data.dao.TaskDao
import ibu.edu.unitask.data.models.Task



@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class UniTaskDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var Instance: UniTaskDatabase? = null

        fun getDatabase(context: Context): UniTaskDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UniTaskDatabase::class.java, "unitask_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{ Instance = it }
            }
        }
    }
}