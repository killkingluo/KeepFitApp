package com.example.keepfitapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomePageBarChart(goalSteps: Int, currentSteps: Int) {
    val percentage: Float = if (goalSteps == 0) 0F
    else if (currentSteps > goalSteps) 1F
    else currentSteps.toFloat() / goalSteps.toFloat()

    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        backgroundColor = Color(0xFFEDB8F5),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                text = "Progress: ${String.format("%.2f", currentSteps.toFloat() / goalSteps.toFloat() * 100)}%"
            )
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
            ) {
                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                )
                drawRoundRect(
                    color = Color(0xFFD6E1FF),
                    size = Size(width = this.size.width * percentage, height = this.size.height),
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                )
            }
        }
    }
}
