package com.example.tmdb.common

import androidx.appcompat.app.AppCompatActivity
import com.example.tmdb.common.utils.isConnected

abstract class BaseActivity : AppCompatActivity() {
    abstract fun onNoConnection()

    override fun onResume() {
        super.onResume()
        if (!isConnected()) {
            onNoConnection()
        }

    }
}