package com.example.kavyakanaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kavyakanaja.ui.AppNav
import com.example.kavyakanaja.ui.theme.KavyakanajaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KavyakanajaTheme {
                AppNav()
            }
        }
    }
}