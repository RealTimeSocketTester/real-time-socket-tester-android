package com.example.sockettesterandroid.features.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sockettesterandroid.ui.theme.Green
import com.example.sockettesterandroid.ui.theme.LightGray
import com.example.sockettesterandroid.ui.theme.PrimaryAccent

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    isEnable: Boolean = true,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth(),
    color: Color = Green,
    progressColor: Color = Color.White,
) {
    Button(
        modifier = modifier,
        onClick = {
            if (!isLoading) {
                onClick()
            }
        },
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = LightGray,
        ),
    ) {
        if (!isLoading) Text(text)
        else CircularProgressIndicator(
            modifier = Modifier.size(25.dp),
            strokeWidth = 3.dp,
            color = progressColor,
        )
    }
}