package com.example.keepfitapp.ui.page

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.HistoryCardDemo
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.components.YesOrNoAlertDialog
import java.util.*

@Composable
fun HistoryPage(navController: NavController, recordViewModel: RecordViewModel, goalViewModel: GoalViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    val openCalendarDialog = remember { mutableStateOf(false) }
    val recordListState = recordViewModel.getAllRecords().collectAsState(initial = listOf())

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDateText = remember { mutableStateOf("") }

// Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker,
          selectedYear: Int,
          selectedMonth: Int,
          selectedDayOfMonth: Int -> selectedDateText.value = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"},
        year,
        month,
        dayOfMonth
    )

    Column(modifier = Modifier.padding(5.dp).fillMaxSize()) {
//        CardDemo(4090, "Weekly   ")
//        CardDemo(5090, "Last week   ")
//        CardDemo(1000, "7 day AVG   ")
        Text(
            text = "Tip: You can click the edit button to edit a record",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(modifier = Modifier.weight(2f)) {
            items(recordListState.value.size) { index ->
                val record = recordListState.value[index]
                HistoryCardDemo(record = record, navController = navController, recordViewModel = recordViewModel)
            }
        }
        //Select data button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { datePicker.show() }
            ) {
                Text("Add or edit a record")
            }
        }

        //ButtonDemo("Add a record", navController, "AddRecordPage")

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
}