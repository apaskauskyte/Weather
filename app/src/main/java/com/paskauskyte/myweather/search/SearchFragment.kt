package com.paskauskyte.myweather.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.city.CityWeather
import com.paskauskyte.myweather.databinding.FragmentSearchBinding
import com.paskauskyte.myweather.search.recyclerview.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var recyclerAdapter: SearchAdapter? = null

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setupSearchView()
        addCityList()
        observeCityWeather()
    }

    private fun setUpRecyclerView() {
        binding.searchRecyclerView.apply {
            recyclerAdapter = SearchAdapter { city -> onCityClick(city) }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setupSearchView() {
        binding.searchView.addTextChangedListener {
            val text = it?.toString() ?: return@addTextChangedListener
            viewModel.findPlace(text)
        }
    }

    private fun addCityList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityListStateFlow.collect { cities ->
                    submitCities(cities)
                }
            }
        }
    }

    private fun submitCities(list: List<City>) {
        recyclerAdapter?.submitList(list)
        binding.searchRecyclerView.adapter = recyclerAdapter
    }

    private fun onCityClick(city: City) {
        viewModel.loadWeather(city)
    }

    private fun observeCityWeather() {
        viewModel.cityWeatherLiveData.observe(viewLifecycleOwner) { cityWeather ->
            transferDataToCityFragment(cityWeather)
            parentFragmentManager.popBackStack()
        }
    }

    private fun transferDataToCityFragment(cityWeather: CityWeather) {
        val bundle = bundleOf(KEY_SOURCE_CITY to cityWeather)
        setFragmentResult(REQUEST_KEY_CITY, bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "search_fragment"
        const val REQUEST_KEY_CITY = "city_fragment_result_key"
        const val KEY_SOURCE_CITY = "key_source_city"
        fun newInstance() = SearchFragment()
    }
}