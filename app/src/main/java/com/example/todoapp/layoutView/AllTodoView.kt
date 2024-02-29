package com.example.todoapp.layoutView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.comman.Screen
import com.example.todoapp.database.Todo
import com.example.todoapp.database.TodoViewModel

@Composable
fun AllTodoView(
    todo: Todo,
    navController: NavController,
    viewModel: TodoViewModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp)
            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(id = R.color.primary_color),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(text = todo.details, color = Color.Black, fontSize = 12.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    val id = todo.id
                    navController.navigate(Screen.AddEditScreen.route + "/$id")
                }, modifier = Modifier.padding()) {
                    Icon(
                        painter = painterResource(id = R.drawable.pencil),
                        tint = colorResource(id = R.color.secondary_color),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                    )
                }
                IconButton(onClick = {
                    viewModel.deleteTodo(todo)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        tint = colorResource(id = R.color.secondary_color),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
                IconButton(onClick = {
                    if (todo.isDone) {
                        val updateTodo = Todo(todo.id, todo.title, todo.details, isDone = false)
                        viewModel.updateTodo(updateTodo)
                    } else {
                        val updateTodo = Todo(todo.id, todo.title, todo.details, isDone = true)
                        viewModel.updateTodo(updateTodo)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.check_circle),
                        tint = if (todo.isDone) Color.Green else colorResource(id = R.color.secondary_color),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}