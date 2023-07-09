package com.paskauskyte.myweather.search.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.paskauskyte.myweather.databinding.FragmentSearchListBinding
import com.paskauskyte.myweather.search.City

class SearchAdapter(
    private val onClick: (City) -> Unit
) : ListAdapter<City, SearchViewHolder>(
    Comparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder(
        FragmentSearchListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false),
        onClick
    )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class Comparator : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: City, newItem: City) =
            oldItem == newItem
    }
}