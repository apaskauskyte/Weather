package com.paskauskyte.myweather.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.paskauskyte.myweather.Constants
import com.paskauskyte.myweather.Constants.ADD_TO_HISTORY_KEY
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.databinding.FragmentHistoryBinding
import com.paskauskyte.myweather.history.recyclerview.HistoryAdapter
import com.paskauskyte.myweather.search.City

class HistoryFragment : Fragment() {

    private var recyclerAdapter: HistoryAdapter? = null

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getCitySearchHistoryList()
    }

    private fun setUpRecyclerView() {
        binding.historyRecyclerView.apply {
            recyclerAdapter = HistoryAdapter { city -> }
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun getCitySearchHistoryList() {
        val sharedPref =
            activity?.getSharedPreferences(
                Constants.HISTORY_SHARED_PREFS_NAME,
                Context.MODE_PRIVATE
            ) ?: return
        val citySearchHistoryListAsString =
            sharedPref.getString(ADD_TO_HISTORY_KEY, emptyList<City>().toString())

        val listType = object : TypeToken<List<City>>() {}.type
        val citySearchHistoryList =
            Gson().fromJson<List<City>>(citySearchHistoryListAsString, listType).toMutableList()

        submitCities(citySearchHistoryList.reversed())
    }

    private fun submitCities(list: List<City>) {
        recyclerAdapter?.submitList(list)
        binding.historyRecyclerView.adapter = recyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
    }

    companion object {
        const val TAG = "history_fragment"

        fun newInstance() = HistoryFragment()
    }
}