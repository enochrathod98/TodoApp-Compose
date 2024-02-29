package com.example.todoapp.comman

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.database.TodoViewModel
import androidx.navigation.NavHostController
import com.example.todoapp.screens.AddEditScreen
import com.example.todoapp.screens.AllScreen
import com.example.todoapp.screens.CompletedScreen


@Composable
fun Navigation(
    viewModel: TodoViewModel,
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = Screen.AllScreen.route,
    ) {
        composable(Screen.AllScreen.route) { AllScreen(navController, viewModel) }
        composable(Screen.CompletedScreen.route) { CompletedScreen(viewModel) }
        composable(Screen.AddEditScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) { entry ->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            AddEditScreen(id = id, viewModel = viewModel, navController = navController)
        }
    }
}