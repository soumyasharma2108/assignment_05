package com.neelasurya.myapplication.injection


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.neelasurya.myapplication.feature.post.PostListViewModel


class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            // Write a message to the database
            var mDatabase: DatabaseReference
            try {
                val database = FirebaseDatabase.getInstance()
                database.setPersistenceEnabled(true)
                mDatabase = database.reference
                mDatabase.keepSynced(true)
            } catch (e: Exception) {
                val database = FirebaseDatabase.getInstance()
                mDatabase = database.reference
            }
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(mDatabase) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}




