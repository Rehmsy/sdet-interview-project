package com.example.sdetinterviewproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.sdetinterviewproject.databinding.ActivityMessageValidationBinding

class MessageValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.let {
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
        }

        with(binding) {
            textInputEditText.addTextChangedListener {
                it?.let { editable ->
                    binding.buttonSubmit.isEnabled = editable.length > 4
                }
            }
            buttonSubmit.setOnClickListener {
                makeVisibleIfNot()
                textInputEditText.text?.clear()
            }
            buttonNext.setOnClickListener {
                val intent = Intent(this@MessageValidationActivity, GifActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun makeVisibleIfNot() {
        with(binding) {
            if (imageView.isVisible.not()) {
                imageView.isVisible = true
            }
            if (imageText.isVisible.not()) {
                imageText.isVisible = true
            }
            if (buttonNext.isVisible.not()) {
                buttonNext.isVisible = true
            }
        }
    }
}
