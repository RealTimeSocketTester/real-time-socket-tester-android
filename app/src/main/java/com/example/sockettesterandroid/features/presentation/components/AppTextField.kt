package com.example.sockettesterandroid.features.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sockettesterandroid.ui.theme.Green
import com.example.sockettesterandroid.ui.theme.LightGray
import com.example.sockettesterandroid.ui.theme.PrimaryAccent
import com.example.sockettesterandroid.ui.theme.SoftGray

@Composable
fun AppTextField(
    value: String = "",
    onValueChange: (data: String) -> Unit,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    message: String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
    isEnable: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(text = label)
        },
        keyboardOptions = keyboardOptions,
        isError = isError,
        supportingText = if (isError && message.trim().isNotEmpty() && message.trim()
                .isNotBlank()
        ) {
            {
                Text(message)
            }
        } else null,
        enabled = isEnable,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAccent,
            focusedLabelColor = LightGray,
            focusedTextColor = LightGray,
            unfocusedBorderColor = SoftGray,
            unfocusedTextColor = LightGray,
            unfocusedLabelColor = Color.White,
        )
    )
}