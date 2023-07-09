package com.paskauskyte.myweather

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.paskauskyte.myweather.city.CityFragment
import com.paskauskyte.myweather.databinding.ActivityWeatherBinding
import com.paskauskyte.myweather.search.SearchFragment
import com.paskauskyte.myweather.settings.SettingsActivity

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}