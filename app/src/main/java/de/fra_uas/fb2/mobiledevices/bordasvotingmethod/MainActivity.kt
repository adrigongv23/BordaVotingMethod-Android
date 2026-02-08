package de.fra_uas.fb2.mobiledevices.bordasvotingmethod

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("voteCount", 0)
        editor.apply()

        val num_options = findViewById<TextInputEditText>(R.id.options)
        var numf : Int = 0

        num_options.setOnFocusChangeListener{v, hasFocus ->
            if(!hasFocus){
                val num = num_options.text.toString().toIntOrNull()

                if(numf != 0 && numf != num){
                    val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    for(i in 0 until numf)
                        editor.remove("Vote$i")

                    editor.apply()
                    Toast.makeText(this, "All votes resetted!", Toast.LENGTH_SHORT).show()
                }
                when {
                    //the user has not enter a number
                    num == null -> Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()

                    num < 2 -> {
                        numf = 2
                        Toast.makeText(this, "Number out of range, has been updated to 2", Toast.LENGTH_SHORT).show()
                    }

                    num > 10 -> {
                        numf = 10
                        Toast.makeText(this, "Number out of range, has been updated to 10", Toast.LENGTH_SHORT).show()
                    }

                    else -> numf = num
                }

                num_options.setText(numf.toString())
            }
        }

        var validOptions = ArrayList<String>()
        val options = findViewById<TextInputEditText>(R.id.comma)

        options.setOnFocusChangeListener {v, hasFocus ->
            if(!hasFocus){
                val opt = options.text.toString().split(",").filter {it.isNotEmpty()}

                if(validOptions != opt){
                    val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    for(i in 0 until numf)
                        editor.remove("Vote$i")

                    editor.apply()
                    Toast.makeText(this, "All votes resetted!", Toast.LENGTH_SHORT).show()
                }

                if (opt.size > numf) {
                    validOptions = ArrayList(opt.take(numf))
                    Toast.makeText(this, "Options superfluous", Toast.LENGTH_SHORT).show()

                } else if (opt.size < numf) {

                    val missing_options = ((opt.size + 1)..numf).map {"Option $it"}
                    validOptions = ArrayList(opt + missing_options)
                    Toast.makeText(this, "Missing Options, autofilling...", Toast.LENGTH_SHORT).show()

                } else{
                    validOptions = ArrayList(opt)
                }
            }
        }

        val button = findViewById<Button>(R.id.AddVote)
        button.setOnClickListener{
            num_options.clearFocus()
            options.clearFocus()
            dataSecondActivity(numf, validOptions)
        }

        val switch = findViewById<Switch>(R.id.VotingResults)
        val textView = findViewById<TextView>(R.id.Results)

        textView.text = validOptions.joinToString("\n")

        switch.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked){

                val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

                val votes = IntArray(numf) {i ->
                    sharedPreferences.getInt("Vote$i", 0)
                }

                val maxVotesIndex = votes.maxOrNull()

                val scoresText = validOptions.indices.joinToString("\n") { i ->
                    if ( i == maxVotesIndex){
                        "**${validOptions[i]}: ${votes[i]} points**"
                    } else {
                        "${validOptions[i]}: ${votes[i]} points"
                    }
                }
                textView.text = scoresText

            }
            else {
                textView.setText("")
            }
        }

        val startOverButton = findViewById<Button>(R.id.Start)
        val voteCounterTextView = findViewById<TextView>(R.id.Nvotes)

        startOverButton.setOnClickListener{
            val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.clear()
            editor.putInt("voteCount", 0)
            editor.apply()

            Toast.makeText(this, "Reset Data", Toast.LENGTH_SHORT).show()
            num_options.setText("")
            options.setText("")
            textView.setText("")
            voteCounterTextView.setText("0")
        }
    }

    private fun dataSecondActivity(numOptions: Int, options: ArrayList<String>) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("numberOfOptions", numOptions)
        Log.d("MainActivity", "Enviando opciones: ${options}")
        intent.putStringArrayListExtra("options", ArrayList(options))
        startActivity(intent)
    }

    override fun onResume(){
        super.onResume()
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val voteCounterTextView = findViewById<TextView>(R.id.Nvotes)
        val voteCount = sharedPreferences.getInt("voteCount", 0)
        // Log.i("doe not matter", "${voteCount}")
        voteCounterTextView.text = "$voteCount"
    }
}