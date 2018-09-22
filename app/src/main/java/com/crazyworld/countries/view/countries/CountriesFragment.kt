package com.crazyworld.countries.view.countries

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import com.crazyworld.countries.common.Constants.BUNDLE_COUNTRY
import com.crazyworld.countries.common.ViewTypeDelegateAdapter
import kotlinx.android.synthetic.main.countries_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {

    private val adapter: DelegateAdapter by inject()
    private val viewModel by viewModel<CountriesViewModel>()

    override fun resource(): Int = R.layout.countries_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.delegateAdapters.apply { applyCountryType() }
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.fall_down)
        countriesList.layoutAnimation = controller
        countriesList.adapter = adapter
        countriesList.scheduleLayoutAnimation()
    }

    override fun render() {
        viewModel.progress.observe(this, Observer { progress.visibility = it ?: View.GONE })
        viewModel.failure.observe(this, Observer { println("error ${it?.message}") })
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is DataReady -> adapter.addData(state.data)
            }
        })
    }

    private fun SparseArrayCompat<ViewTypeDelegateAdapter>.applyCountryType() {
        put(CountryItemType.ItemType, DelegateCountryAdapter { country ->
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_COUNTRY, country)
            findNavController().navigate(R.id.action_countries_to_details, bundle)
        })
    }
}