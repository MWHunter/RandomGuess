package edu.rockvalleycollege.randomguess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get random number 1-100000
        var number = ((Math.random() * 100001) + 1).toInt()
        //Get random number 0-100000
        //var number = (Math.random () * 100001) .toInt ()
        //Toast is an easy way to alert user. Shows up on bottom of app
        var lastToast = Toast.makeText(this, "Number to remember: ${number}", Toast.LENGTH_LONG)
        lastToast.show()
        val txtGuess = findViewById<EditText>(R.id.txtGuess)
        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val btnToast = findViewById<Button>(R.id.btnToast)

        btnGuess.setOnClickListener {
            try {
                if (number == txtGuess.text.toString().toInt()) {
                    hideKeyboard()
                    lastToast.cancel()
                    lastToast = Toast.makeText(this, "Correct, Great Job Remembering.", Toast.LENGTH_LONG)
                    lastToast.show()
                    txtGuess.setText("")
                } else {
                    hideKeyboard()
                    lastToast.cancel()
                    lastToast = Toast.makeText(this, "InCorrect,  it's not the number shown.", Toast.LENGTH_LONG)
                    lastToast.show()
                    txtGuess.setText("")
                }
            } catch (e: NumberFormatException) {
                lastToast.cancel()
                lastToast = Toast.makeText(this, "This is not a valid number!", Toast.LENGTH_LONG)
                lastToast.show()
            }

        }
        btnToast.setOnClickListener {
            number = ((Math.random() * 100001) + 1).toInt()
            println(number)
            lastToast.cancel()
            lastToast = Toast.makeText(this, "Number to remember: ${number}", Toast.LENGTH_LONG)
            lastToast.show()
        }

        //Fire hidekeyboard when user taps outside any text object
//Place below code right before last right bracket in function onCreate
        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }

    }

    //function to hide keyboard goes right before the last right bracket of Class MainActivity
//should auto import android.content.Context
//should auto add import android.view.inputmethod.InputMethodManager
    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }

    }
}