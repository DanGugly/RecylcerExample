package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todolistapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var taskAdapter: TaskAdapter


    // Set click listener to be used by any button
    private val addingTask = View.OnClickListener {
        Intent(baseContext, TaskActivity::class.java).apply {
            startActivityForResult(this, TASK_REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = TaskAdapter()

        binding.addButton.setOnClickListener(addingTask)
    }

    override fun onResume() {
        super.onResume()
        //Set recycler into adapter view
        //binding.todoRecycler.adapter = taskAdapter
        binding.todoRecycler.apply {
            adapter = taskAdapter
            layoutManager = GridLayoutManager(baseContext, 3)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == TASK_REQUEST_CODE
            && resultCode == RESULT_OK
            && data != null)
            {
                //Get new task from the intent returned
                val newTask = data.getParcelableExtra<PojoTodo>(TaskActivity.NEW_TASK_DATA)
                //If newTask is null do nothing, if not do what's inside
                newTask?.let {
                    taskAdapter.updateDataSet(it)
                }
        }
        else{
            Toast.makeText(baseContext,"No Task Added", Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        private const val TASK_REQUEST_CODE = 21
    }
}