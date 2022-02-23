package com.example.tiptime

import android.icu.text.NumberFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }
    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option20 -> 0.20
            R.id.option18 -> 0.18
            else -> 0.15
        }
        var tip = (tipPercentage * cost)
        var tong = cost + (tipPercentage * cost)
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
            tong = kotlin.math.ceil(tong)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val formattedTong = NumberFormat.getCurrencyInstance().format(tong)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        binding.tongResult.text = getString(R.string.tong_amount, formattedTong)
    }
}
