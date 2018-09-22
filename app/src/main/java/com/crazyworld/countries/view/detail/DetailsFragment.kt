package com.crazyworld.countries.view.detail

import android.os.Bundle
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import com.crazyworld.countries.common.Constants
import com.crazyworld.countries.view.countries.CountriesRender

class DetailsFragment : BaseFragment() {

    private val country by lazy {
        arguments?.getParcelable<CountriesRender>(Constants.BUNDLE_COUNTRY)
    }



    override fun render() {

    }

    override fun resource(): Int = R.layout.detail_fragment
}