package com.paskauskyte.myweather.city_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var viewModel: CityViewModel

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "city_fragment"
        fun newInstance() = CityFragment()
    }
}