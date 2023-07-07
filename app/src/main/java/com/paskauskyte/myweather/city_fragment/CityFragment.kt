package com.paskauskyte.myweather.city_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.size.ViewSizeResolver
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.databinding.FragmentCityBinding
import kotlinx.coroutines.launch

class CityFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels()

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cityWeatherStateFlow.collect {
                    binding.apply {
                        cityTextView.text = "Vilnius"
                        countryTextView.text = "Lithuania"
                        descriptionTextView.text = "Sunny with a chance of rain"
                        temperatureTextView.text = "26"
                        val photoPath = "https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2022/03/the_sun_in_high_resolution/24010613-1-eng-GB/The_Sun_in_high_resolution_pillars.jpg"
                        weatherIconImageView.load(photoPath) {
                            size(ViewSizeResolver(weatherIconImageView))
                        }
                    }
                }
            }
        }
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
        const val TAG = "city_fragment"
        fun newInstance() = CityFragment()
    }
}