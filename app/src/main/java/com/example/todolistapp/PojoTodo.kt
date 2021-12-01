package com.example.todolistapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Annotation to add parcelable implementation
@Parcelize
data class PojoTodo(
    var task:String,
    var category: String
) : Parcelable