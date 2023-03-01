package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.ui.components.textFieldDemo


@Composable
fun GoalAddPage(navController: NavController, goalViewModel: GoalViewModel) {
    val selectGoal = goalViewModel.getCurrentSelectGoal()
    var newGoalName: String by remember { mutableStateOf(selectGoal.name) }
    var newGoalSteps: String by remember { mutableStateOf(selectGoal.steps.toString()) }
    var inputGoalNameError by remember { mutableStateOf(false) }
    var inputGoalStepsError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Goal Name", modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
        newGoalName = textFieldDemo(KeyboardType.Text, textFieldValue = newGoalName , checkType = 1)
        Text(
            text = "Target Step Number",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
        newGoalSteps =
            textFieldDemo(KeyboardType.Number, textFieldValue = if (newGoalSteps == "0") "" else newGoalSteps, checkType = 0)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    inputGoalNameError = inputCheck(text = newGoalName, regex = "^[a-zA-Z].*")
                    inputGoalStepsError = inputCheck(text = newGoalSteps, regex = "^\\d{1,7}\$")
                    if (!inputGoalNameError && !inputGoalStepsError) {
                        when (selectGoal.id) {
                            -1 -> goalViewModel.insertGoal(
                                Goal(
                                    name = newGoalName,
                                    steps = newGoalSteps.toInt()
                                )
                            )
                            else -> goalViewModel.insertGoal(
                                Goal(
                                    id = selectGoal.id,
                                    name = newGoalName,
                                    steps = newGoalSteps.toInt(),
                                    activityFlag = selectGoal.activityFlag
                                )
                            )
                        }
                        navController.navigate(Screen.GoalSetting.route) {
                            popUpTo(Screen.GoalSetting.route) { inclusive = true }
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun ErrorMessage(meaasge: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = meaasge,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
        )
    }
}
