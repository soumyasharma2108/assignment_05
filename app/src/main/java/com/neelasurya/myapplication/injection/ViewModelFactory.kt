package com.neelasurya.myapplication.injection


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.neelasurya.myapplication.feature.post.PostListViewModel



class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            // Write a message to the database
            val database = Firebase.database
            val reference = database.reference
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(reference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}




