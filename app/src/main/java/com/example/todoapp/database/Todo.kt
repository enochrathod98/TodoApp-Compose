package com.example.todoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoTable")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "todo-title")
    val title: String = "",
    @ColumnInfo(name = "todo-desc")
    val details: String = "",
    @ColumnInfo(name = "todo-done")
    val isDone: Boolean = false,
)

object DummyDataList {

    val dummyData = listOf(
        Todo(
            id = 0,
            title = "Morning",
            details = "Eat Breakfast at 7 AM. Just checking the space limit in the Text view of the card item.",
            isDone = false
        ),
        Todo(id = 0, title = "Afternoon", details = "Eat Lunch at 1 PM", isDone = false),
        Todo(id = 0, title = "Evening", details = "Drink Tea at 5 PM ", isDone = false),
        Todo(id = 0, title = "Night", details = "Eat Dinner at 8 PM", isDone = false),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = false),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = false),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = false),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = false),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true),
        Todo(id = 0, title = "Sleep", details = "Go to bed at 10 PM", isDone = true)
    )
}