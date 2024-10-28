package com.example.happybirthday

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View // Import View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var textViewResult: TextView

    private val currencies = arrayOf("USD", "EUR", "VND") // Thay đổi nếu cần
    private val conversionRates = mapOf(
        "USD" to mapOf("EUR" to 0.85, "VND" to 23000.0),
        "EUR" to mapOf("USD" to 1.18, "VND" to 27000.0),
        "VND" to mapOf("USD" to 0.000043, "EUR" to 0.000037)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAmount = findViewById(R.id.editTextAmount)
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        textViewResult = findViewById(R.id.textViewResult)

        setupSpinners()
        setupListeners()
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter
    }

    private fun setupListeners() {
        editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateConversion()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                calculateConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                calculateConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun calculateConversion() {
        val amountString = editTextAmount.text.toString()
        if (amountString.isEmpty()) {
            textViewResult.text = "Kết quả: "
            return
        }

        val amount = amountString.toDouble()
        val fromCurrency = spinnerFromCurrency.selectedItem.toString()
        val toCurrency = spinnerToCurrency.selectedItem.toString()

        val rate = conversionRates[fromCurrency]?.get(toCurrency) ?: 1.0
        val convertedAmount = amount * rate

        textViewResult.text = "Kết quả: %.2f %s".format(convertedAmount, toCurrency)
    }
}
