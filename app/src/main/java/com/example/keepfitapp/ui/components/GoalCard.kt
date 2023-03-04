package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel


@Composable
fun GoalCardDemo(
    navController: NavController,
    goal: Goal,
    goalViewModel: GoalViewModel,
    recordViewModel: RecordViewModel,
    userSettingViewModel: UserSettingViewModel
) {
    val goalEditable = userSettingViewModel.goalEditable.collectAsState(initial = false)
    val openDeleteDialog = remember { mutableStateOf(false) }
    val openEditableDialog = remember { mutableStateOf(false) }
    val openActivityGoalEditDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        elevation = 10.dp,
        backgroundColor = when (goal.activityFlag) {
            0 -> Color(0xFFE9D7F7)
            else -> Color(0xFFD6E1FF)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp).weight(weight = 1f, fill = false),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontWeight = FontWeight.W900,
                    fontSize = 15.sp,
                    letterSpacing = 1.sp,
                    text = "Goal name:" + goal.name
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp,
                                color = Color(0xFF4552B8),
                            )
                        )
                        {
                            append(goal.steps.toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 15.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append(" Steps")
                        }
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //edit button
                if (goalEditable.value && goal.activityFlag == 0) {
                    IconButton(
                        onClick = {
                            goalViewModel.setCurrentSelectGoal(goal = goal)
                            navController.navigate(route = Screen.GoalAdd.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            tint = Color.Black,
                            contentDescription = null
                        )
                        if (openEditableDialog.value) {
                            SimpleAlertDialog(
                                title = "Warning",
                                alertContent = "You set goal to be non-editable.",
                                onDismiss = { openEditableDialog.value = false }
                            )
                        } else if (openActivityGoalEditDialog.value) {
                            SimpleAlertDialog(
                                title = "Warning",
                                alertContent = "You cannot edit an activity goal!",
                                onDismiss = { openActivityGoalEditDialog.value = false }
                            )
                        }
                    }
                }
                //delete button
                if (goal.activityFlag == 0) {
                    IconButton(
                        onClick = { openDeleteDialog.value = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            tint = Color.Black,
                            contentDescription = null
                        )
                        if (openDeleteDialog.value) {
                            YesOrNoAlertDialog(
                                title = "Delete Confirmation",
                                alertContent = "Please confirm whether you want to delete goal ${goal.name}?",
                                onDismiss = { openDeleteDialog.value = false },
                                toDO = {
                                    goalViewModel.deleteGoal(goal)
                                    openDeleteDialog.value = false
                                }
                            )
                        }
                    }
                }
                //activity button
                IconButton(
                    onClick = {
                        goalViewModel.newActivityGoal(goal.id)
                        recordViewModel.updateTargetSteps(
                            target_steps = goal.steps,
                            date = getTodayTimestamp()
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
