package com.example.todoapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.layoutView.AppBarView
import com.example.todoapp.database.Todo
import com.example.todoapp.database.TodoViewModel
import com.example.todoapp.layoutView.TodoTextFieldView
import kotlinx.coroutines.launch

@Composable
fun AddEditScreen(
    id: Long,
    viewModel: TodoViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    rememberScaffoldState()
    if (id != 0L) {
        val wish = viewModel.getTodoById(id).collectAsState(initial = Todo(0L, "", ""))
        viewModel.todoTitleState = wish.value.title
        viewModel.todoDescriptionState = wish.value.details
    } else {
        viewModel.todoTitleState = ""
        viewModel.todoDescriptionState = ""
    }

    Scaffold(topBar = {
        AppBarView(
            title = if (id != 0L) stringResource(id = R.string.Update_todo) else stringResource(
                id = R.string.Add_todo
            )
        ) { navController.navigateUp() }
    }) { it ->

        val it = it
        Column(
            modifier = Modifier
                .padding(top = 50.dp, start = 8.dp, end = 8.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            TodoTextFieldView(label = "Title",
                value = viewModel.todoTitleState,
                onValueChanged = {
                    viewModel.onTodoTitleChanged(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            TodoTextFieldView(label = "Description",
                value = viewModel.todoDescriptionState,
                onValueChanged = {
                    viewModel.onTodoDescriptionChanged(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (viewModel.todoTitleState.isNotEmpty() &&
                        viewModel.todoDescriptionState.isNotEmpty()
                    ) {
                        if (id != 0L) {
                            viewModel.updateTodo(
                                Todo(
                                    id = id,
                                    title = viewModel.todoTitleState.trim(),
                                    details = viewModel.todoDescriptionState.trim(),
                                )
                            )
                        } else {
                            viewModel.addTodo(
                                Todo(
                                    title = viewModel.todoTitleState.trim(),
                                    details = viewModel.todoDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Wish has been created"
                        }
                    } else {
                        //
                        snackMessage.value = "Enter fields to create a wish"
                    }
                    scope.launch {
                        navController.navigateUp()
                    }
                }) {
                Text(
                    text =
                    if (id != 0L) stringResource(id = R.string.update_todo)
                    else stringResource(
                        id = R.string.add_todo
                    ),
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}

