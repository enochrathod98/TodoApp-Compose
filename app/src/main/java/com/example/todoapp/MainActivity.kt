package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.layoutView.BottomBarView
import com.example.todoapp.comman.Navigation
import com.example.todoapp.comman.Screen
import com.example.todoapp.comman.Tab
import com.example.todoapp.database.TodoViewModel
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: TodoViewModel = viewModel()
                    val selectedTab by viewModel.selectedTab.collectAsState()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Scaffold(
                        bottomBar = {
                            Log.e("XXX", currentDestination?.route.toString())
                            if (currentDestination?.route != "AddEdit_Screen/{id}") {
                                BottomBarView(navController = navController, viewModel)
                            }
                        },
                        floatingActionButton = {
                            if (selectedTab == Tab.All && currentDestination?.route != "AddEdit_Screen/{id}") {
                                FloatingActionButton(
                                    modifier = Modifier
                                        .padding(all = 20.dp),
                                    contentColor = Color.White,
                                    backgroundColor = colorResource(id = R.color.primary_color),
                                    onClick = {
                                        ///  Toast.makeText(context, "FAButton Clicked", Toast.LENGTH_LONG).show()
                                        navController.navigate(Screen.AddEditScreen.route + "/0L")

                                    }) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                                }
                            }
                        }
                    ) { innerPadding ->
                        val innerPadding = innerPadding
                        Navigation(viewModel, navController)
                    }
                }
            }
        }
    }
}

