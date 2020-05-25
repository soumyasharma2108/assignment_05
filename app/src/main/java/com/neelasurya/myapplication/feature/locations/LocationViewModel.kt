package com.neelasurya.myapplication.feature.locations

import android.location.Location
import com.google.firebase.database.FirebaseDatabase


object MapUtils {
    fun init() = FirebaseDatabase.getInstance().getReference("currentLocation")


    fun updateLocation(location: Location) {
        if (location != null) {
            init().setValue(location)
        }
    }


}