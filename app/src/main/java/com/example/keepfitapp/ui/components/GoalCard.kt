package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.theme.Blue200
import com.example.keepfitapp.ui.theme.Blue700

@Composable
fun GoalCardDemo(goal: Goal, goalViewModel: GoalViewModel, recordViewModel: RecordViewModel) {
    val openDialog = remember { mutableStateOf(false) }

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
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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
            Row(
                //modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //activity button
                IconButton(
                    onClick = {
                        if(goal.activityFlag == 0) {
                            goalViewModel.cancelActivityGoal()
                            goalViewModel.newActivityGoal(goal.id)
                            recordViewModel.updateTargetSteps(goal.steps, date = getTodayTimestamp())
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        tint = Color.Black,
                        contentDescription = null)
                }
                //delete button
                IconButton( onClick = { openDialog.value = true } ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        tint = Color.Black,
                        contentDescription = null)
                    if(goal.activityFlag == 1){
                        SimpleAlertDialog(title = "Warning",
                            alertContent = "You cannot delete an active target",
                            openDialog.value, onDismiss = { openDialog.value = false }
                        )
                    }
                    else {
                        yesOrNoAlertDialog(
                            title = "Delete Confirmation",
                            alertContent = "Please confirm whether you want to delete goal?",
                            openDialog = openDialog.value,
                            onDismiss = { openDialog.value = false },
                            toDO = {
                                goalViewModel.delete(goal)
                            }
                        )
                    }
                }
            }
        }
    }
}
