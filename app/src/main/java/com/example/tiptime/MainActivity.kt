package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click behaviour for calculate button
        binding.calculateButton.setOnClickListener{calculateTip() }
    }

    private fun calculateTip(){
        // Get text in cost of service edit text and convert to cost (double type)
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }


        // Get the chosen radion button and convert data to percentage
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }

        // Calculate the tip and round
        var tip = tipPercentage * cost

        // Check if round up switch is enabled, if yes -> round up tip
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        // Format tip to the proper currency
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Display tip
        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
