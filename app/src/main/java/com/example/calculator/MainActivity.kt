package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var calcIn: TextView? = null
    private var lastDot = false
    private var lastDigit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calcIn = findViewById(R.id.calcIn)

    }

    fun onDigit(view: View) {
        calcIn?.append((view as Button).text)
        lastDigit = true
    }

    fun onCLR(view: View) {
        calcIn?.text = ""
        lastDot = false
        lastDigit = false
        (view as Button).text.also(::println)
    }

    fun dot(view: View) {
        if (!lastDot) {
            calcIn?.append((view as Button).text)
            lastDot = true
        }
    }

    fun arithmetic(view: View) {
        calcIn?.text?.let {
            if (lastDigit && (it.last() != '.')) {
                calcIn?.append((view as Button).text)
                lastDigit = false
                lastDot = false
            }
        }

    }

    fun equalTo(view: View) {
        (view as Button).text.also(::println)
        var exp: String? = calcIn?.text?.toString()
        if (exp?.last() == '/' || exp?.last() == '*') {
            exp += "1"

        } else if (exp?.last() == '+' || exp?.last() == '-') {
            exp += "0"
        }
        calcIn?.text=""
        lastDot=true
        lastDigit=true

            calcIn?.text = calc(exp).toString()


    }
}
