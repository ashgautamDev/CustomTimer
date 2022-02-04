package com.ashish.custometimer.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.ashish.custometimer.navigation.NavGraph
import com.ashish.custometimer.ui.theme.CustomeTimerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomeTimerTheme {
                // A surface container using the 'background' color from the theme
                NavGraph()
//                TimerScreen()
            }
        }
    }
}
