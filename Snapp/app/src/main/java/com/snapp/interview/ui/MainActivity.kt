package com.snapp.interview.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.snapp.interview.R
import com.snapp.interview.ui.browse.BrowseFragment
import com.snapp.interview.ui.map.MapFragment
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            when {
                isConnectedToInternet() -> replaceFragment(MapFragment.newInstance())
                else -> replaceFragment(BrowseFragment.newInstance())
            }
        }
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivity =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivity?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}