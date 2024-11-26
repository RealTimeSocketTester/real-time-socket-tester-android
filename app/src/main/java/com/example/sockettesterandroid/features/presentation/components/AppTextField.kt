package com.example.sockettesterandroid.features.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTextField(
    value: String = "",
    onValueChange: (data: String) -> Unit,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    message: String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
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
        supportingText = if (isError) {
            { Text(text = message) }
        } else null,
    )
}