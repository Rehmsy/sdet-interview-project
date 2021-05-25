package com.example.sdetinterviewproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.sdetinterviewproject.databinding.ActivityGifBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class GifActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar?.let {
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
        }
        with(binding) {
            userQueryEditText.addTextChangedListener {
                it?.let { editable ->
                    binding.submitButton.isEnabled = editable.length > 2
                }
            }
            submitButton.setOnClickListener {
                getGIF(binding.userQueryEditText.editableText.toString())
                userQueryEditText.text?.clear()
            }
        }
        loadGIF()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadGIF(gifPath: String = getString(R.string.big_horned_sheep_gif)) {
        val radius = 30
        val margin = 10
        val drawableProgressBar = CircularProgressDrawable(this)
        drawableProgressBar.strokeWidth = 5.0F
        drawableProgressBar.centerRadius = 50F
        drawableProgressBar.setColorSchemeColors(Color.CYAN)
        drawableProgressBar.start()
        Glide.with(this)
            .asGif()
            .placeholder(drawableProgressBar)
            .error(android.R.drawable.stat_notify_error)
            .load(gifPath)
            .transform(RoundedCornersTransformation(radius, margin))
            .into(binding.gifImageView)
    }

    private fun getGIF(userQuery: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$BASE_URL?api_key=$API_KEY&q=$userQuery&rating=g")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@GifActivity, e.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val payload = JSONObject(
                    response.body?.string() ?: ""
                )
                runOnUiThread {
                    val imageData = payload.getJSONArray(getString(R.string.data)).getJSONObject(0)
                    loadGIF(
                        imageData
                            .getJSONObject(getString(R.string.images))
                            .getJSONObject(getString(R.string.original))
                            .getString(getString(R.string.url))
                            ?: getString(R.string.big_horned_sheep_gif)
                    )
                    val gifTitle = imageData.getString(getString(R.string.gif_title))
                    if (gifTitle.isEmpty()) Log.i(getString(R.string.tag), getString(R.string.gif_title_empty))
                    else Log.i(getString(R.string.tag), getString(R.string.gif_title_string, gifTitle))
                    binding.gifTitle.text = gifTitle
                }
            }
        })
    }
}
