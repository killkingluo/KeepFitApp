package com.example.keepfitapp.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.TextFieldDemo
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.function.timeStampToDate
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel

@Composable
fun EditRecordPage(navController: NavController, goalViewModel: GoalViewModel, recordViewModel: RecordViewModel) {
    val currentSelectedRecord = recordViewModel.getCurrentSelectedRecord() //当前选中的记录
    var inputSteps: String by remember{ mutableStateOf(currentSelectedRecord.current_steps.toString()) }
    var inputGoalSteps by remember{ mutableStateOf(currentSelectedRecord.target_steps) } //当前的目标步数
    var inputStepsErrorFlag by remember{ mutableStateOf(0) }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "You are editing ${timeStampToDate(currentSelectedRecord.joined_date)}'s record",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(text = "Add steps",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        //输入步数
        inputSteps = TextFieldDemo(KeyboardType.Number, textFieldValue = "")
        when (inputStepsErrorFlag) {
            1 -> ErrorMessage(meaasge = "The number of steps cannot be empty")
            2 -> ErrorMessage(meaasge = "Please enter number")
        }
        Text(text = "Select a new goal",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        //选择目标
        inputGoalSteps = goalDropdownMenu(inputGoalSteps, goalViewModel)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    //check input
                    inputStepsErrorFlag = inputCheck(text = inputSteps, regex = "^[0-9]\\d*$")
                    if (inputStepsErrorFlag == 0) {
                        recordViewModel.updateCurrentSteps(
                            current_steps = currentSelectedRecord.current_steps + inputSteps.toInt(),
                            date = currentSelectedRecord.joined_date
                        )
                        recordViewModel.updateTargetSteps(
                            target_steps = inputGoalSteps,
                            date = currentSelectedRecord.joined_date
                        )
                        navController.navigate(Screen.History.route) {
                            popUpTo(Screen.History.route) {inclusive = true}
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
fun goalDropdownMenu(currentGoalSteps: Int, goalViewModel: GoalViewModel): Int {
    var goalSteps = remember { mutableStateOf(currentGoalSteps) }
    val expanded = remember { mutableStateOf(false) }
    val goalListState = goalViewModel.getAllGoals.collectAsState(initial = listOf())

    Box(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize(Alignment.TopStart),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = { expanded.value = true },
            modifier = Modifier.background(Color.LightGray).fillMaxWidth()
        ) {
            Text(text = "${goalSteps.value} steps")
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            for(item in goalListState.value) {
                DropdownMenuItem(
                    onClick = {
                        expanded.value = false
                        goalSteps.value =  item.steps
                    }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = item.name + " " + item.steps.toString() + " steps",
                    )
                }
            }
        }
    }
    return goalSteps.value
}