package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmicalculator.databinding.TodoMainBinding // Import the generated binding class for todo_main.xml

class TodoMain : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: TodoMainBinding // Declare the binding variable with the correct type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using the correct binding class
        binding = TodoMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set the root view of the binding

        todoAdapter = TodoAdapter(mutableListOf())

        // Access views through the binding object (should now work correctly)
        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}