package com.masterstroke.app.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MasterstrokeColors = lightColorScheme(
    primary = Color(0xFF0B5A6B),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFC7EEF4),
    onPrimaryContainer = Color(0xFF00363F),
    secondary = Color(0xFF4D6166),
    surface = Color(0xFFFAFDFC),
    background = Color(0xFFFAFDFC),
    error = Color(0xFFBA1A1A),
)

@Composable
fun MasterstrokeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = MasterstrokeColors, content = content)
}
