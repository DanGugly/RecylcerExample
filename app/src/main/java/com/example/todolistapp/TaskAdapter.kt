package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(

    private val taskList: MutableList<PojoTodo> = mutableListOf()
) : RecyclerView.Adapter<TaskViewHolder>(){

    //private val taskList = emptyList<PojoTodo>()

    //Update new data to the list of tasks PojoTodo
    fun updateDataSet(tasks: PojoTodo){
        taskList.add(tasks)
        //This will notify that data has changed and adapter has to update data
        notifyDataSetChanged()
    }

    //Inflate items that are going to be displayed in the Recycler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val taskView = LayoutInflater.from(parent.context).inflate(
            R.layout.tasks_items,
            parent,
            false
        )
        return TaskViewHolder(taskView)
    }

    //Onbindviewholder will set the data to your view holder
    // Each view will be populated
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskTitle.text = taskList[position].task
        holder.taskCategory.text = taskList[position].category
    }

    override fun getItemCount() : Int = taskList.size
}

class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val taskTitle : TextView = itemView.findViewById(R.id.task_title)
    val taskCategory : TextView = itemView.findViewById(R.id.task_category)
}