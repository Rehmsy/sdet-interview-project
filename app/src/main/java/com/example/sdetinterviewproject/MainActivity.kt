package com.example.sdetinterviewproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sdetinterviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            brown.setOnClickListener(
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
            grey.setOnClickListener(
                generateClickListener(
                    ColorData(resources.getString(R.string.grey), ColorOptions.GREY)
                )
            )
        }
    }
    private fun generateClickListener(colorData: ColorData) = View.OnClickListener {
//        showHidden(binding.textView)
//        enableNextButton(binding.buttonNext)
        showHiddenTextAndEnableNextButtonIfTrue()
        binding.textView.text = String.format(
            resources.getString(R.string.color_text),
            colorData.colorString
        )
        setTextColor(colorData.colorOptions.colorString)
    }
    private fun showHiddenTextAndEnableNextButtonIfTrue() {
        when (binding.textView.visibility) {
            View.INVISIBLE ->
                binding.textView.visibility = View.VISIBLE
        }
        when {
            !binding.buttonNext.isEnabled ->
                binding.buttonNext.isEnabled = true
        }
    }

//    private fun showHidden(view: View) {
//        if (view.visibility == View.INVISIBLE) {
//            view.visibility = View.VISIBLE
//        }
//    }
//
//    private fun enableNextButton(view: View) {
//        if (!view.isEnabled) {
//            view.isEnabled = true
//        }
//    }

    private fun setTextColor(color: Int) {
        binding.textView.setTextColor(color)
    }
    fun navigateToSecondView(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
