package com.paskauskyte.myweather.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.paskauskyte.myweather.Constants.CELSIUS_ON_KEY
import com.paskauskyte.myweather.Constants.TEMP_SCALE_SHARED_PREFS_NAME
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readTempScaleSettingButton()
    }

    fun onScaleButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.celsius ->
                    if (checked) {
                        saveTempScaleSettingButton(true)
                    }
                R.id.fahrenheit ->
                    if (checked) {
                        saveTempScaleSettingButton(false)
                    }
            }
        }
    }

    private fun saveTempScaleSettingButton(value: Boolean) {
        val sharedPref = this.getSharedPreferences(TEMP_SCALE_SHARED_PREFS_NAME, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(CELSIUS_ON_KEY, value)
            apply()
        }
    }

    fun readTempScaleSettingButton() {
        val sharedPref = this.getSharedPreferences(TEMP_SCALE_SHARED_PREFS_NAME, Context.MODE_PRIVATE) ?: return
        val defaultValue = true
        val celsiusIsOn = sharedPref.getBoolean(CELSIUS_ON_KEY, defaultValue)

        if (celsiusIsOn) {
            binding.celsius.isChecked = true
            binding.fahrenheit.isChecked = false
        } else {
            binding.celsius.isChecked = false
            binding.fahrenheit.isChecked = true
        }
    }
}