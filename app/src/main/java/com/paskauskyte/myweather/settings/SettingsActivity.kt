package com.paskauskyte.myweather.settings

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.paskauskyte.myweather.R
import com.paskauskyte.myweather.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onScaleButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.celsius ->
                    if (checked) {
                        // Celsius on
                    }
                R.id.fahrenheit ->
                    if (checked) {
                        // Fahrenheit on
                    }
            }
        }
    }
}