package com.example.sockettesterandroid.core.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.sockettesterandroid.ui.theme.BackColor1
import com.example.sockettesterandroid.ui.theme.BackColor2

object AppUtility {

    fun getDefaultAppBackground(): Brush {
        return Brush.linearGradient(
            colors = listOf(BackColor1, BackColor2), // Your gradient colors
            start = Offset(0f, 0f), // Gradient start point
            end = Offset(1000f, 1000f), // Gradient end point (direction)
        )
    }

}