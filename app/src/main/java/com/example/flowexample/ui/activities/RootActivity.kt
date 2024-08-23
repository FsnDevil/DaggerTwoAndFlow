package com.example.flowexample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowexample.R
import dagger.android.support.DaggerAppCompatActivity

open class RootActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }
}