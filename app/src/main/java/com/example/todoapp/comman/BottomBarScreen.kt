package com.example.todoapp.comman

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object ALl : BottomBarScreen(
        route = "All_Screen",
        title = "All",
        icon = Icons.AutoMirrored.Filled.List
    )

    data object Completed :
        BottomBarScreen(
            route = "Completed_Screen",
            title = "Completed",
            icon = Icons.Default.Done
        )
}