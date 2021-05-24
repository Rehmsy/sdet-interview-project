package com.example.sdetinterviewproject

import android.graphics.Color

data class ColorData(
    val colorString: String,
    val colorOptions: ColorOptions
)

enum class ColorOptions(val colorString: Int) {
    BROWN(Color.parseColor("#500e0c")),
    PINK(Color.parseColor("#FA4988")),
    GREEN(Color.parseColor("#13840f")),
    RED(Color.parseColor("#9c0412")),
    BLUE(Color.parseColor("#728ef0")),
    YELLOW(Color.parseColor("#FFD700")),
    BLACK(Color.parseColor("#000000")),
    GREY(Color.parseColor("#6D6A6A"))
}
