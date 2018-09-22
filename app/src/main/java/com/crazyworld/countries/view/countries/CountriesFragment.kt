package com.crazyworld.countries.view.countries

import android.arch.lifecycle.Observer
import android.view.View
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import kotlinx.android.synthetic.main.countries_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {

    private val viewModel by viewModel<CountriesViewModel>()

    override fun resource(): Int = R.layout.countries_fragment

    override fun render() {
        viewModel.progress.observe(this, Observer { progress.visibility = it ?: View.GONE })
        viewModel.failure.observe(this, Observer { println("error ${it?.message}") })
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is DataReady -> println("Success")

            }
        })
    }
}