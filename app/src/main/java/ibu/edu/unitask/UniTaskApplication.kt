package ibu.edu.unitask

import android.app.Application
import ibu.edu.unitask.data.graph.Graph

class UniTaskApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}