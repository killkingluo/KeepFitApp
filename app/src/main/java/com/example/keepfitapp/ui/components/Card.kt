package com.example.keepfitapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepfitapp.domain.model.Goal

@Composable
fun CardDemo(steps: Int,cardName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900,fontSize = 20.sp, letterSpacing = 1.sp))
                        {
                                append(cardName)
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900,fontSize = 40.sp, letterSpacing = 1.sp, color = Color(0xFF4552B8))
                        ) {
                            append(steps.toString())
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900,fontSize = 20.sp, letterSpacing = 1.sp))
                        {
                            append(" Steps")
                        }
                    }
                )
            }
        }
    }
}
