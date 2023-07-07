package com.paskauskyte.myweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.paskauskyte.myweather.city_fragment.CityFragment
import com.paskauskyte.myweather.databinding.ActivityWeatherBinding
import com.paskauskyte.myweather.search_fragment.SearchFragment

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

            openCityFragment()
    }

    private fun openCityFragment() {
        setCurrentFragment(CityFragment.newInstance(), CityFragment.TAG)
    }

    fun openSearchFragment() {
        setCurrentFragment(SearchFragment.newInstance(), SearchFragment.TAG, true)
    }

    private fun setCurrentFragment(fragment: Fragment, tag: String, addBackStack: Boolean = false) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container_view,
                fragment,
                tag
            )

            setReorderingAllowed(true)

            if (addBackStack) {
                addToBackStack(tag)
            }
        }
    }
}