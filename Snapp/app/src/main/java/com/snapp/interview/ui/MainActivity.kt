package com.snapp.interview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.snapp.interview.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}