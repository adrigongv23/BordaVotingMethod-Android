package de.fra_uas.fb2.mobiledevices.bordasvotingmethod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_activity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val numberOptions = intent.getIntExtra("numberOfOptions", 0)
        Log.i("doe not matter", "num of options $numberOptions")
        val options = intent.getStringArrayListExtra("options")
        Log.i("doe not matter", "as many ${options}")

        if (options != null){

            val seekBars = arrayOf(
                findViewById<SeekBar>(R.id.seekBar1), findViewById<SeekBar>(R.id.seekBar2),
                findViewById<SeekBar>(R.id.seekBar3), findViewById<SeekBar>(R.id.seekBar4),
                findViewById<SeekBar>(R.id.seekBar5), findViewById<SeekBar>(R.id.seekBar6),
                findViewById<SeekBar>(R.id.seekBar7), findViewById<SeekBar>(R.id.seekBar8),
                findViewById<SeekBar>(R.id.seekBar9), findViewById<SeekBar>(R.id.seekBar10)
            )

            val textViews = arrayOf(
                findViewById<TextView>(R.id.textView1), findViewById<TextView>(R.id.textView2),
                findViewById<TextView>(R.id.textView3), findViewById<TextView>(R.id.textView4),
                findViewById<TextView>(R.id.textView5), findViewById<TextView>(R.id.textView6),
                findViewById<TextView>(R.id.textView7), findViewById<TextView>(R.id.textView8),
                findViewById<TextView>(R.id.textView9), findViewById<TextView>(R.id.textView10)
            )

            for (i in 0 until 10){
                seekBars[i].visibility = View.GONE
                textViews[i].visibility = View.GONE
            }

            for (i in 0 until numberOptions){

                seekBars[i].visibility = View.VISIBLE
                textViews[i].visibility = View.VISIBLE
                textViews[i].text = options[i]
            }

            val points = IntArray(numberOptions)
            val textViewPoints = findViewById<TextView>(R.id.seevotes)

            for (i in 0 until numberOptions){
                seekBars[i].setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                    override fun onProgressChanged( seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        points[i] = progress
                        Log.i("doe not matter", "as many ${options}")
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                        val sortedPoints = points.indices.sortedBy{points[it]}.toIntArray()
                        val bordaPoints = IntArray(numberOptions) {-1}

                        for (i in sortedPoints.indices){
                            if(i < sortedPoints.size -1 && points[sortedPoints[i]] == points[sortedPoints[i+1]]){
                                bordaPoints[sortedPoints[i]] = -2
                                bordaPoints[sortedPoints[i + 1]] = -2

                            } else if (bordaPoints[sortedPoints[i]] != -2){
                                bordaPoints[sortedPoints[i]] = i
                            }
                        }

                        val pointsText = options.indices.joinToString("\n"){ i ->
                            val point = if (bordaPoints[i] == -2) "<not unique>" else "${bordaPoints[i]} point"
                            "${options[i]} = $point"
                        }

                        textViewPoints.setText(pointsText)

                        val confirmButton = findViewById<Button>(R.id.confirmvote)

                        confirmButton.setOnClickListener{
                            if(bordaPoints.any {it == -2}) {
                                Toast.makeText(this@SecondActivity, "There are equal values, please change them", Toast.LENGTH_SHORT).show()
                            } else{
                                val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()

                                for(i in 0 until numberOptions){
                                    val existingVotes = sharedPreferences.getInt("Vote$i", 0)
                                    val newVotes = existingVotes + bordaPoints[i]
                                    editor.putInt("Vote$i", newVotes)
                                }

                                val voteCount = sharedPreferences.getInt("voteCount", 0)
                                editor.putInt("voteCount", voteCount + 1)
                                Log.i("doe not matter", "${voteCount}")

                                editor.apply()
                                finish()
                            }
                        }
                    }
                })
            }
        }

        val cancel = findViewById<Button>(R.id.buttonCancel)
        cancel.setOnClickListener {
            finish()
        }

    }
}

