package com.example.todoapp.layoutView

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todoapp.R
import com.example.todoapp.comman.Tab
import com.example.todoapp.comman.BottomBarScreen
import com.example.todoapp.database.TodoViewModel

@Composable
fun BottomBarView(navController: NavHostController, viewModel: TodoViewModel) {

    BottomNavigation {
        val items = listOf(
            BottomBarScreen.ALl,
            BottomBarScreen.Completed
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = colorResource(id = R.color.primary_color)
                    )
                },
                modifier = Modifier.background(Color.White),
                label = { Text(item.title, color = colorResource(id = R.color.primary_color)) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        if (item.title == Tab.All.name) {
                            viewModel.selectTab(Tab.All)
                        } else {
                            viewModel.selectTab(Tab.Completed)
                        }
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}