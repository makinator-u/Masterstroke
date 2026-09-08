package com.masterstroke.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.masterstroke.app.presentation.MasterstrokeApp
import com.masterstroke.app.presentation.theme.MasterstrokeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterstrokeTheme { MasterstrokeApp() }
        }
    }
}
