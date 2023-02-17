package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
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
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.ui.theme.Blue200
import com.example.keepfitapp.ui.theme.Blue700

@Composable
fun GoalCardDemo(goal: Goal, goalViewModel: GoalViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp,
        backgroundColor = when(goal.activityFlag){
            0 -> Blue200
            else -> Blue700
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
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
                                append(goal.name)
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900,fontSize = 40.sp, letterSpacing = 1.sp, color = Color(0xFF4552B8))
                            ) {
                                append(goal.steps.toString())
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.W900,fontSize = 20.sp, letterSpacing = 1.sp))
                            {
                                append(" Steps")
                            }
                        }
                    )
                }
            }
            IconButton(
                onClick = {
                    goalViewModel.cancelActivityGoal()
                    goalViewModel.newActivityGoal(goal.id)
                }
            ) {Icon(Icons.Filled.Done, null)}

        }
    }
}