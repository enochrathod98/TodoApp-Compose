package com.example.todoapp.database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.comman.Tab
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository = Graph.todoRepository) :
    ViewModel() {

    private val _selectedTab = MutableStateFlow(Tab.All)
    val selectedTab = _selectedTab.asStateFlow()

    fun selectTab(tab: Tab) {
        _selectedTab.value = tab
    }

    var todoTitleState by mutableStateOf("")
    var todoDescriptionState by mutableStateOf("")


    fun onTodoTitleChanged(newString: String) {
        todoTitleState = newString
    }

    fun onTodoDescriptionChanged(newString: String) {
        todoDescriptionState = newString
    }

    lateinit var getAllTodo: Flow<List<Todo>>

    init {
        viewModelScope.launch {
            getAllTodo = todoRepository.getTodo()
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addTodo(todo)
        }
    }

    fun getTodoById(id: Long): Flow<Todo> {
        return todoRepository.getTodoById(id)
    }

    fun getTodoByCompleted(isDone: Boolean): Flow<List<Todo>> {
        return todoRepository.getTodoByCompleted(isDone)
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
            getAllTodo = todoRepository.getTodo()
        }
    }
}