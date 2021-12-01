package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolistapp.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.saveTask.setOnClickListener{
            //Validate empty fields
            if(checkEmptyValues()){
                Toast.makeText(baseContext,"Please enter data", Toast.LENGTH_LONG).show()
            }else{
                setResult(RESULT_OK, setData())
                finish()
            }
        }
    }

    //Check if any empty values entered
    private fun checkEmptyValues(): Boolean{
        // We get values entered for task and category
        val task = binding.titleTask.editText?.text.toString()
        val category = binding.catTask.editText?.text.toString()

        return task.isEmpty() || category.isEmpty()
    }

    private fun setData(): Intent{
        //Get data from edittexts
        val task = binding.titleTask.editText?.text.toString()
        val category = binding.catTask.editText?.text.toString()
        //Construct task object to be sent to previous activity
        val newTask = PojoTodo(task, category)
        //Return intent with data inside
        return Intent().putExtra(NEW_TASK_DATA, newTask)
    }

    companion object{
        const val NEW_TASK_DATA = "NEW_TASK_DATA"
    }
}