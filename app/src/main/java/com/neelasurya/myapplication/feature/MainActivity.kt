package com.neelasurya.myapplication.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.neelasurya.myapplication.R
import com.neelasurya.myapplication.databinding.ActivityMainBinding
import com.neelasurya.myapplication.feature.locations.LocationTrackingActivity
import com.neelasurya.myapplication.ui.post.PostListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.tracking.setOnClickListener {
            startActivity(Intent(this@MainActivity, LocationTrackingActivity::class.java))
        }

        binding.order.setOnClickListener {
            startActivity(Intent(this@MainActivity, PostListActivity::class.java))
        }


    }

}
