package com.neelasurya.myapplication.model

import android.view.View
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Order() {
    var address: String? = null
    var assigned_time: String? = null
    var contact: String? = null
    var id: Int = 0
    var name: String? = null
    var status = 0
    @Exclude
    var isExpanded: Int = View.GONE

}