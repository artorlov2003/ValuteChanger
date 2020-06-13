package com.artorlov2003.valutechanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        fun getCourse(valute: String) =
            when (valute) {
                "GBP" -> 0.7901392316
                "RUB" -> 74.0
                "EUR" -> 0.8812125485
                "NOK" -> 9.4465985196
                "USD" -> 1.0
                "PLN" -> 3.941575608
                else -> 0.0
            } as Double
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstValute.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                try {
                    if (firstValute.isFocused) {
                        if (p0.toString() == "") {
                            secoundValute.setText("")
                            return
                        }
                        var fVal: Double = p0.toString().toDouble()
                        var course : Double = getCourse(fspinner.selectedItem.toString())
                        val inUSD = fVal / course
                        course = getCourse(sspinner.selectedItem.toString())
                        var sVal = inUSD * course
                        secoundValute.setText(sVal.toString())
                    }
                } catch (e0: Exception) {
                    Toast.makeText(applicationContext, e0.message, Toast.LENGTH_SHORT).show()
                    print(e0.message)
                }


            }


            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })
        secoundValute.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                try {
                    if (secoundValute.isFocused) {
                        if (p0.toString() == "") {
                            firstValute.setText("")
                            return
                        }
                        var sVal: Double = p0.toString().toDouble()
                        var course : Double = getCourse(sspinner.selectedItem.toString())
                        val inUSD = sVal / course
                        course = getCourse(fspinner.selectedItem.toString())
                        var fVal = inUSD * course
                        firstValute.setText(fVal.toString())
                    }
                } catch (e0: Exception) {
                    Toast.makeText(applicationContext, e0.message, Toast.LENGTH_SHORT).show()
                    print(e0.message)
                }


            }


            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }
        })

        fspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = fspinner.getItemAtPosition(position).toString()
                firstValute.setHint(selectedItem)
                var p0 = firstValute.text
                if (p0.toString() == "") {
                    secoundValute.setText("")
                    return
                }
                var fVal: Double = p0.toString().toDouble()
                var course : Double = getCourse(fspinner.selectedItem.toString())
                val inUSD = fVal / course
                course = getCourse(sspinner.selectedItem.toString())
                var sVal = inUSD * course
                secoundValute.setText(sVal.toString())
            }

        }
        sspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = sspinner.getItemAtPosition(position).toString()
                secoundValute.setHint(selectedItem)
                var p0 = secoundValute.text
                if (p0.toString() == "") {
                    firstValute.setText("")
                    return
                }
                var sVal: Double = p0.toString().toDouble()
                var course : Double = getCourse(sspinner.selectedItem.toString())
                val inUSD = sVal / course
                course = getCourse(fspinner.selectedItem.toString())
                var fVal = inUSD * course
                firstValute.setText(fVal.toString())
            }

        }

    }
}