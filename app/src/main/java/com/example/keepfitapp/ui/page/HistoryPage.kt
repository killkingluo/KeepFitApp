package com.example.keepfitapp.ui.page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.ui.components.HistoryCardDemo
import com.example.keepfitapp.domain.function.localdateToTimestamp
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel
import com.example.keepfitapp.ui.components.YesOrNoAlertDialog
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryPage(
    navController: NavController,
    recordViewModel: RecordViewModel,
    userSettingViewModel: UserSettingViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val historyEditable = userSettingViewModel.historyEditable.collectAsState(initial = false)
    val recordListState = recordViewModel.getAllRecords().collectAsState(initial = listOf())
    val pickDate = remember { mutableStateOf(LocalDate.now()) }
    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        if (historyEditable.value) {
            Text(
                text = "Tip: You can click the edit button to edit a record",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(recordListState.value.size) { index ->
                val record = recordListState.value[index]
                HistoryCardDemo(
                    record = record,
                    navController = navController,
                    recordViewModel = recordViewModel,
                    userSettingViewModel = userSettingViewModel
                )
            }
        }
        //Select data button
        if (historyEditable.value) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        dateDialogState.show()
                    }
                ) {
                    Text("Select data to add steps")
                }
            }
        }
        //Delete Button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { openDialog.value = true }
            ) {
                Text("Delete All History")
                if (openDialog.value) {
                    YesOrNoAlertDialog(
                        title = "Delete Confirmation",
                        alertContent = "Please confirm whether you want to delete all records(Include today)?",
                        onDismiss = { openDialog.value = false },
                        toDO = {
                            recordViewModel.deleteAllRecord()
                            recordViewModel.initialization()
                            openDialog.value = false
                        }
                    )
                }
            }
        }
    }
    //日期选择器
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                //set currentSelectedRecord
                recordViewModel.getSelectDateRecord(localdateToTimestamp(pickDate.value))
                navController.navigate(route = Screen.EditRecord.route)
            }
            negativeButton(text = "Cancel")
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a data",
        ) {
            pickDate.value = it
        }
    }
}