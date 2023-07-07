package com.paskauskyte.myweather.search_fragment.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.paskauskyte.myweather.databinding.FragmentSearchListBinding
import com.paskauskyte.myweather.search_fragment.City

class SearchViewHolder(
    private val binding: FragmentSearchListBinding,
    private val onClick: (City) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var currentCity: City? = null

    init {
        binding.root.setOnClickListener { currentCity?.let { city -> onClick(city) } }
    }

    fun bind(city: City) {
        currentCity = city
        binding.apply {
            cityTextView.text = city.name
            countryTextView.text = city.country
        }
    }
}