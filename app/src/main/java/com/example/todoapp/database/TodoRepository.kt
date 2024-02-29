package com.example.todoapp.database

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDAO) {

    suspend fun addTodo(wish: Todo) {
        todoDao.addTodo(wish)
    }

    fun getTodo(): Flow<List<Todo>> = todoDao.getAllTodo()

    fun getTodoById(id: Long): Flow<Todo> {
        return todoDao.getTodoById(id)
    }

    fun getTodoByCompleted(isDone: Boolean): Flow<List<Todo>> {
        return todoDao.getTodoByCompleted(isDone)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}