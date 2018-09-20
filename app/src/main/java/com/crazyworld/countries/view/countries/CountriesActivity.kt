package com.crazyworld.countries.view.countries

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesActivity : BaseActivity() {

    private val viewModel by viewModel<CountriesViewModel>()

    override fun render() {
        viewModel.progress.observe(this, Observer { progress.visibility = it ?: View.GONE })
        viewModel.failure.observe(this, Observer { println("error ${it?.message}") })
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is DataReady -> println("Success")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
