package com.paskauskyte.myweather.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import coil.load
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.WeatherActivity
import com.paskauskyte.myweather.databinding.FragmentCityBinding
import com.paskauskyte.myweather.search.SearchFragment

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

        receiveDataFromSearchFragment()
        onClickSearchButton()
        observeCityWeather()
    }

    private fun onClickSearchButton() {
        binding.openSearchButton.setOnClickListener {
            val activity = activity as WeatherActivity
            activity.openSearchFragment()
        }
    }

    private fun observeCityWeather() {
        viewModel.cityWeatherLiveData.observe(viewLifecycleOwner) { cityWeather ->
            binding.apply {
                cityTextView.text = cityWeather.city.substringBefore(',')
                countryTextView.text = cityWeather.country
                descriptionTextView.text = cityWeather.description
                temperatureTextView.text = cityWeather.temperature.toString() + "°C"
                val photoId = cityWeather.weatherIcon
                val url = "https://openweathermap.org/img/wn/$photoId@2x.png"
                weatherIconImageView.load(url)
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

    private fun receiveDataFromSearchFragment() {
        setFragmentResultListener(SearchFragment.REQUEST_KEY_CITY) { requestKey, bundle ->
            val cityWeather = bundle.getParcelable<CityWeather>(SearchFragment.KEY_SOURCE_CITY)
                ?: return@setFragmentResultListener
            viewModel.saveCityWeather(cityWeather)
        }
    }

    companion object {
        const val TAG = "city_fragment"
        fun newInstance() = CityFragment()
    }
}