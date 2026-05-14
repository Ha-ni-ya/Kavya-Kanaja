package com.example.kavyakanaja.ui.theme.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kavyakanaja.data.Poem

@Composable
fun PremiumPoemCard(poem: Poem, navController: NavController) {

    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        animationSpec = tween(200),
        label = "scale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .scale(scale)
            .clickable {
                pressed = true
                navController.navigate("poem_detail/${poem.id}")
            },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.12f)
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFFffffff).copy(alpha = 0.08f),
                            Color(0xFFffffff).copy(alpha = 0.03f)
                        )
                    )
                )
                .padding(14.dp)
        ) {

            Text(
                poem.title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                "— ${poem.poet}",
                color = Color.White.copy(alpha = 0.7f)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                poem.text.take(80) + "...",
                color = Color.White.copy(alpha = 0.85f)
            )
        }
    }
}