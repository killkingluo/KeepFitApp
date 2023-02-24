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
import com.example.keepfitapp.TextFieldDemo
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel


@Composable
fun GoalAddPage(navController: NavController, goalViewModel: GoalViewModel) {
    val selectGoal = goalViewModel.getCurrentSelectGoal()
    var newGoalName: String by remember{ mutableStateOf(selectGoal.name)}
    var newGoalSteps: String by remember{ mutableStateOf(selectGoal.steps.toString())}
    var newGoalNameErrorFlag by remember{ mutableStateOf(0)}
    var newGoalStepsErrorFlag by remember{ mutableStateOf(0)}

    Column(modifier = Modifier.padding(5.dp).fillMaxHeight(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Goal Name",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        newGoalName = TextFieldDemo(KeyboardType.Text, textFieldValue = newGoalName)
        when (newGoalNameErrorFlag) {
            1 -> ErrorMessage(meaasge = "Please enter the target name")
            2 -> ErrorMessage(meaasge = "Please enter a string beginning with a letter")
        }
        Text(text = "Target Step Number",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        newGoalSteps = TextFieldDemo(KeyboardType.Number, textFieldValue = newGoalSteps)
        when (newGoalStepsErrorFlag) {
            1 -> ErrorMessage(meaasge = "Please enter the target steps")
            2 -> ErrorMessage(meaasge = "Please enter number")
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    newGoalNameErrorFlag = inputCheck(text = newGoalName, regex = "^\\w.*")
                    newGoalStepsErrorFlag = inputCheck(text = newGoalSteps, regex = "^[1-9]\\d*$")
                    if (newGoalNameErrorFlag == 0 && newGoalStepsErrorFlag == 0) {
                        when(selectGoal.id) {
                            -1 -> goalViewModel.insertGoal(Goal(name = newGoalName, steps = newGoalSteps.toInt()))
                            else -> goalViewModel.insertGoal(Goal(id = selectGoal.id, name = newGoalName, steps = newGoalSteps.toInt(), activityFlag = selectGoal.activityFlag))
                        }
                        navController.navigate(Screen.GoalSetting.route) {
                            popUpTo(Screen.GoalSetting.route) {inclusive = true}
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
fun ErrorMessage(meaasge: String){
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
