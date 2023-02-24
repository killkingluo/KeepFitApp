package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.CardDemo
import com.example.keepfitapp.TextFieldDemo
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.RecordViewModel


@Composable
fun LogStepsPage(navController: NavController, recordViewModel: RecordViewModel) {
    var inputSteps: String by remember{ mutableStateOf("") }
    var inputStepsErrorFlag by remember{ mutableStateOf(0)}
    val currentRecordState = recordViewModel.getCurrentRecord(getTodayTimestamp()).collectAsState(initial = null).value

    Column(modifier = Modifier.padding(5.dp)) {
        CardDemo(steps = currentRecordState?.current_steps?: 0, cardName = "Current   ")
        inputSteps = TextFieldDemo(KeyboardType.Number, textFieldValue = "")
        when (inputStepsErrorFlag) {
            1 -> ErrorMessage(meaasge = "Please enter the target steps")
            2 -> ErrorMessage(meaasge = "Please enter number")
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    inputStepsErrorFlag = inputCheck(text = inputSteps, regex = "^[1-9]\\d*$")
                    if (inputStepsErrorFlag == 0) {
                        recordViewModel.updateCurrentSteps(
                            current_steps = currentRecordState?.current_steps?.plus( inputSteps.toInt() ) ?: 0,
                            date = getTodayTimestamp()
                        )
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) {inclusive = true}
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }
    }
}