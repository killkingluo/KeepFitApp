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
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.function.timeStampToDate
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.components.CardDemo
import com.example.keepfitapp.ui.components.CurrentBar
import com.example.keepfitapp.ui.components.textFieldDemo

@Composable
fun EditRecordPage(
    navController: NavController,
    goalViewModel: GoalViewModel,
    recordViewModel: RecordViewModel
) {
    val currentSelectedRecord = recordViewModel.getCurrentSelectedRecord() //当前选中的记录
    var inputSteps by remember { mutableStateOf(currentSelectedRecord.current_steps.toString()) }
    var inputGoalSteps by remember { mutableStateOf(currentSelectedRecord.target_steps) }
    var inputError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "You are editing ${timeStampToDate(currentSelectedRecord.joined_date)}'s record",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        CurrentBar(currentSteps = currentSelectedRecord.current_steps)
        Text(
            text = "Add steps", modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
        //输入步数
        inputSteps = textFieldDemo(KeyboardType.Number, textFieldValue = "", checkType = 0, labelId = "Add steps", contentDescription = "Add steps")
        Text(
            text = "Select a new goal",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
        //选择目标
        inputGoalSteps = goalDropdownMenu(inputGoalSteps, goalViewModel)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    //check input
                    inputError = inputCheck(text = inputSteps, regex = "^\\d{1,7}\$")
                    if (!inputError) {
                        var totalSteps = currentSelectedRecord.current_steps + inputSteps.toInt()
                        if (totalSteps > 9999999) {
                            totalSteps = 9999999
                        }
                        if (currentSelectedRecord.id != -1) {
                            recordViewModel.updateRecord(
                                Record(
                                    id = currentSelectedRecord.id,
                                    current_steps = totalSteps,
                                    target_steps = inputGoalSteps,
                                    joined_date = currentSelectedRecord.joined_date
                                )
                            )
                        } else {
                            recordViewModel.insertRecord(
                                Record(
                                    current_steps = totalSteps,
                                    target_steps = inputGoalSteps,
                                    joined_date = currentSelectedRecord.joined_date
                                )
                            )
                        }
                        navController.navigate(Screen.History.route) {
                            popUpTo(Screen.History.route) { inclusive = true }
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
    val goalSteps = remember { mutableStateOf(currentGoalSteps) }
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
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {
            Text(text = "${goalSteps.value} steps")
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            for (item in goalListState.value) {
                DropdownMenuItem(
                    onClick = {
                        expanded.value = false
                        goalSteps.value = item.steps
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