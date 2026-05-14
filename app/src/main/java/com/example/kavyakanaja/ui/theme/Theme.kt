package com.example.kavyakanaja.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    primary = Color(0xFFD0BCFF),
    secondary = Color(0xFFCCC2DC),
    tertiary = Color(0xFFEFB8C8)

)

private val LightColorScheme = lightColorScheme(

    primary = Color(0xFF6A1B9A),
    secondary = Color(0xFF9C27B0),
    tertiary = Color(0xFFE1BEE7)

)

@Composable
fun KavyakanajaTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = true,

    content: @Composable () -> Unit

) {

    val colorScheme = when {

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {

            val context = LocalContext.current

            if (darkTheme)
                dynamicDarkColorScheme(context)

            else
                dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    MaterialTheme(

        colorScheme = colorScheme,

        typography = Typography,

        content = content
    )
}