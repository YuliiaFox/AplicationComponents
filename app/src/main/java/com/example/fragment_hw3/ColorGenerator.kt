package com.example.fragment_hw3

import android.graphics.Color
import kotlin.random.Random

fun generateColor(): Int {
    return Color.argb(
        255,
        Random.nextInt(0, 255),
        Random.nextInt(0, 255),
        Random.nextInt(0, 255)
    )
}