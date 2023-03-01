package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel

@Composable
fun SettingsPage(userSettingViewModel: UserSettingViewModel) {
    val isGoalEditable = userSettingViewModel.goalEditable.collectAsState(initial = false)
    val isHistoryEditable = userSettingViewModel.historyEditable.collectAsState(initial = false)

    Column(modifier = Modifier.padding(5.dp)) {
        Row(modifier = Modifier.padding(5.dp)) {
            //goal editable button
            Text(
                text = "Goal Editable",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            Spacer(Modifier.weight(1f))
            Switch(checked = isGoalEditable.value,
                onCheckedChange = {
                    userSettingViewModel.setGoalEditable(it)
                }
            )
        }
        Row(modifier = Modifier.padding(5.dp)) {
            //history editable button
            Text(
                text = "History Editable",
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            Spacer(Modifier.weight(1f))
            Switch(checked = isHistoryEditable.value,
                onCheckedChange = {
                    userSettingViewModel.setHistoryEditable(it)
                }
            )
        }
    }
}