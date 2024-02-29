package com.example.todoapp.database

import android.content.Context
import androidx.room.Room


object Graph {
    lateinit var database: TodoDatabase

    val todoRepository by lazy {
        TodoRepository(todoDao = database.todoDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, TodoDatabase::class.java, "todolist.db").build()
    }
}