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
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.viewmodel.GoalViewModel


@Composable
fun GoalAddPage(navController: NavController, goalViewModel: GoalViewModel) {
    var newGoalName: String by remember{ mutableStateOf("")}
    var newGoalSteps: String by remember{ mutableStateOf("")}
    var newGoalNameErrorFlag by remember{ mutableStateOf(0)}
    var newGoalStepsErrorFlag by remember{ mutableStateOf(0)}

    Column(modifier = Modifier.padding(5.dp).fillMaxHeight(),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Goal Name",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        newGoalName = TextFieldDemo(KeyboardType.Text)
        when (newGoalNameErrorFlag) {
            1 -> errorMessage("Please enter the target name")
            2 -> errorMessage("Please enter a string beginning with a letter")
        }
        Text(text = "Target Step Number",modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6)
        newGoalSteps = TextFieldDemo(KeyboardType.Number)
        when (newGoalStepsErrorFlag) {
            1 -> errorMessage("Please enter the target steps")
            2 -> errorMessage("Please enter number")
        }
//        if (newGoalStepsErrorFlag!=0) {
//            errorMessage("Please enter the target steps")
//        }
        //ButtonDemo("Submit", navController, "GoalSetting")
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    newGoalNameErrorFlag = inputCheck(text = newGoalName, regex = "^\\w.+")
                    newGoalStepsErrorFlag = inputCheck(text = newGoalSteps, regex = "^[1-9]\\d+$")
                    if (newGoalNameErrorFlag == 0 && newGoalStepsErrorFlag == 0) {
                        goalViewModel.insert(Goal(name = newGoalName, steps = newGoalSteps.toInt(), activityFlag = 0))
                        navController.navigate("GoalSetting")
                    }
                }
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun errorMessage(meaasge: String){
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

fun inputCheck(text: String, regex: String): Int {
    if(text == "") {
        return 1 //输入为空
    }
    else if(Regex(pattern = regex).matches(input = text)) {
        return 0 //输入正确
    }
    return 2  //输入错误
}

