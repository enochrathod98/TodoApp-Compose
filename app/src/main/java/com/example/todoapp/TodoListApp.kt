package com.example.todoapp

import android.app.Application
import com.example.todoapp.database.Graph

class TodoListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}