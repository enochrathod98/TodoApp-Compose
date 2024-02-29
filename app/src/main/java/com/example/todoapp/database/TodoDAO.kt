package com.example.todoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addTodo(todoEntity: Todo)

    @Query("Select * from 'todoTable'")
    abstract fun getAllTodo(): Flow<List<Todo>>

    @Update
    abstract suspend fun updateTodo(todoEntity: Todo)

    @Delete
    abstract suspend fun deleteTodo(todoEntity: Todo)

    @Query("Select * from 'todoTable' where id=:id")
    abstract fun getTodoById(id: Long): Flow<Todo>

    @Query("Select * from 'todoTable' where `todo-done`=:isDone")
    abstract fun getTodoByCompleted(isDone: Boolean): Flow<List<Todo>>
}