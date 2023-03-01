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
import com.example.keepfitapp.ui.components.CardDemo
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.function.inputCheck
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.components.textFieldDemo


@Composable
fun LogStepsPage(navController: NavController, recordViewModel: RecordViewModel) {
    var inputSteps: String by remember { mutableStateOf("") }
    val currentRecordState =
        recordViewModel.getCurrentRecord(getTodayTimestamp()).collectAsState(initial = null).value
    var inputError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(5.dp)) {
        CardDemo(steps = currentRecordState?.current_steps ?: 0, cardName = "Current   ")
        inputSteps = textFieldDemo(KeyboardType.Number, textFieldValue = "", checkType = 0)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    inputError = inputCheck(text = inputSteps, regex = "^\\d{1,7}\$")
                    if (!inputError) {
                        var totalSteps =
                            currentRecordState?.current_steps?.plus(inputSteps.toInt()) ?: 0
                        if (totalSteps > 9999999) {
                            totalSteps = 9999999
                        }
                        recordViewModel.updateCurrentSteps(
                            current_steps = totalSteps,
                            date = getTodayTimestamp()
                        )
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }
    }
}