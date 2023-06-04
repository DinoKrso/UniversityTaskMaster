package ibu.edu.unitask.data.graph

import android.content.Context
import ibu.edu.unitask.data.database.UniTaskDatabase
import ibu.edu.unitask.data.repository.OfflineRepository

object Graph {
    lateinit var db:UniTaskDatabase
        private set

    val repository by lazy {
        OfflineRepository(
            TaskDao = db.taskDao()
        )
    }

    fun provide(context: Context){
        db = UniTaskDatabase.getDatabase(context)
    }
}