package com.example.sdetinterviewproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.sdetinterviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            b.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.brown), ColorOptions.BROWN)
                )
            )
            pink.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.pink), ColorOptions.PINK)
                )
            )
            green.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.green), ColorOptions.GREEN)
                )
            )
            red.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.red), ColorOptions.RED)
                )
            )
            blue.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.blue), ColorOptions.BLUE)
                )
            )
            yellow.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.yellow), ColorOptions.YELLOW)
                )
            )
            black.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.black), ColorOptions.BLACK)
                )
            )
            banana.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.grey), ColorOptions.GREY)
                )
            )
            buttonNext.setOnClickListener {
                val intent = Intent(this@MainActivity, MessageValidationActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun generateClickListener(colorData: ColorData) = View.OnClickListener {
        showHiddenTextAndEnableNextButtonIfTrue()
        binding.textView.text = String.format(
            resources.getString(R.string.color_text),
            colorData.colorString
        )
        setTextColor(colorData.colorOptions.colorString)
    }
    private fun showHiddenTextAndEnableNextButtonIfTrue() {
        with(binding) {
            if (textView.isVisible.not()) {
                textView.isVisible = true
            }
            if (buttonNext.isEnabled.not()) {
                buttonNext.isEnabled = true
            }
        }
    }

    private fun setTextColor(color: Int) {
        binding.textView.setTextColor(color)
    }
}
