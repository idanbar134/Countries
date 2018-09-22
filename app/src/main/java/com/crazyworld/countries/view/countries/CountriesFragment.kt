package com.crazyworld.countries.view.countries

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController
import com.crazyworld.countries.R
import com.crazyworld.countries.common.BaseFragment
import com.crazyworld.countries.common.Constants.BUNDLE_COUNTRY
import kotlinx.android.synthetic.main.countries_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {

    private val adapter: DelegateAdapter by inject()
    private val viewModel by viewModel<CountriesViewModel>()

    override fun resource(): Int = R.layout.countries_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.delegateAdapters.apply {
            put(CountryItemType.ItemType, DelegateCountryAdapter {
                val bundle = Bundle()
                bundle.putParcelable(BUNDLE_COUNTRY,it)
                findNavController().navigate(R.id.action_countries_to_details,bundle)
            })
        }
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        countriesList.layoutManager = layoutManager
        countriesList.adapter = adapter
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
}