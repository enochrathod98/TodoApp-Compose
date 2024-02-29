package com.example.todoapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.todoapp.layoutView.AppBarView
import com.example.todoapp.database.TodoViewModel
import com.example.todoapp.layoutView.CenteredText
import com.example.todoapp.layoutView.CompletedView

@Composable
fun CompletedScreen(viewModel: TodoViewModel) {
    val todolist = viewModel.getTodoByCompleted(true).collectAsState(initial = listOf())
    if (todolist.value.isEmpty()) {
        CenteredText(text = "No Completed Task")
    }
    Scaffold(topBar = { AppBarView(title = "TodoApp") }) { innerPadding ->
        if (todolist.value.isEmpty()) {
            CenteredText(text = "No Completed Task")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(todolist.value) { item ->
                if (item.isDone) {
                    CompletedView(todo = item) {
                    }
                }
            }
        }
    }
}