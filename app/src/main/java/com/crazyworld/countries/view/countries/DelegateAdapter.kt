package com.crazyworld.countries.view.countries

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.crazyworld.countries.R
import com.crazyworld.countries.common.*
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.country_item.view.*
import java.util.concurrent.TimeUnit

class DelegateAdapter : BaseDelegateAdapter()

class DelegateCountryAdapter(var onClick: (CountriesRender) -> Unit) : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = CountryHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        (holder as CountryHolder).bind(item as CountriesRender)
    }

    inner class CountryHolder(view: ViewGroup) : RecyclerView.ViewHolder(view.inflate(R.layout.country_item)) {
        fun bind(country: CountriesRender) = with(itemView) {
            name.text = country.name
            population.text = country.population

            RxView.clicks(this)
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe {
                        onClick.invoke(country)
                    }
            backgroundFlag.load(country.flag ?: "")

        }
    }
}



object CountryItemType {
    const val ItemType = 0
}