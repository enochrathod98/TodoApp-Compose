package com.example.todoapp.comman

sealed class Screen(val route: String) {
    data object AllScreen : Screen("All_Screen")
    data object CompletedScreen : Screen("Completed_Screen")
    data object AddEditScreen : Screen("AddEdit_Screen")
}