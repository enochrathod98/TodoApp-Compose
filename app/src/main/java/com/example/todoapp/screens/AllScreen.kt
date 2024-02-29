package com.example.todoapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.database.TodoViewModel
import com.example.todoapp.layoutView.AllTodoView
import com.example.todoapp.layoutView.AppBarView
import com.example.todoapp.layoutView.CenteredText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {
    LocalContext.current
    rememberScaffoldState()
    Scaffold(
        topBar = { AppBarView(title = "TodoApp") },
    ) { innerPadding ->
        val todolist = viewModel.getAllTodo.collectAsState(initial = listOf())
        if (todolist.value.isEmpty()){
            CenteredText(text = "No Task Todo, Please Add New Task")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(todolist.value, key = { todo -> todo.id }) { todo ->
                AllTodoView(todo = todo,navController,viewModel) {
                }
            }
        }
    }
}