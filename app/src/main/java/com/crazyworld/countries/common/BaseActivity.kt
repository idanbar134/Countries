package com.crazyworld.countries.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        render()
    }
    abstract fun render()
}