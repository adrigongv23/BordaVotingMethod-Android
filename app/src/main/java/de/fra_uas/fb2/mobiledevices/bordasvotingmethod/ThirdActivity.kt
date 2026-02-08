package de.fra_uas.fb2.mobiledevices.bordasvotingmethod
/*
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
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
            // Log.i("doe not matter", "num of options $numberOptions")
            val options = intent.getStringArrayListExtra("options")
            // Log.i("doe not matter", "as many ${options}")

            if (options != null) {

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

                for (i in 0 until 10) {
                    seekBars[i].visibility = View.GONE
                    textViews[i].visibility = View.GONE
                }

                for (i in 0 until numberOptions) {

                    seekBars[i].visibility = View.VISIBLE
                    textViews[i].visibility = View.VISIBLE
                    textViews[i].text = options[i]
                }

                val points = IntArray(numberOptions)
                val textViewPoints = findViewById<TextView>(R.id.seevotes)

                for (i in 0 until numberOptions) {
                    seekBars[i].setOnSeekBarChangeListener(object :
                        SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(
                            seekBar: SeekBar?,
                            progress: Int,
                            fromUser: Boolean
                        ) {
                            points[i] = progress

                            val pointsText =
                                options.zip(points.toList()).joinToString("\n") { (option, point) ->
                                    "$option = $point points"
                                }

                            textViewPoints.setText(pointsText)
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar) {
                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar) {
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
}
*/
