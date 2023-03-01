package com.example.keepfitapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePageBarChart(goalSteps: Int, currentSteps: Int) {
    val percentage: Float = if (goalSteps == 0) 0F
    else if (currentSteps > goalSteps) {
        1F
    }
    else {
        currentSteps.toFloat() / goalSteps.toFloat()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontWeight = FontWeight.W900,
                fontSize = 20.sp,
                text = "Achievement: ${String.format("%.2f", currentSteps.toFloat() / goalSteps.toFloat() * 100)}%"
            )
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
            ) {
                drawRoundRect(
                    color = Color.LightGray,
                    size = size,
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                )
                drawRoundRect(
                    color = Color(0xFF4552B8),
                    size = Size(width = this.size.width * percentage, height = this.size.height),
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                )
            }
        }
    }
}
