package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.ui.components.SwitchDemo

@Composable
fun SettingsPage(goalViewModel: GoalViewModel) {
    val isCheck = remember { mutableStateOf(false) }

    if (goalViewModel.getGoalEditableFlag() != 0) {
        isCheck.value = true
    }

    Row(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Goal Editable",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(Modifier.weight(1f))
        Switch(checked = isCheck.value,
            onCheckedChange = {
                isCheck.value = it
                when(isCheck.value) {
                    true -> {
                        goalViewModel.updateGoalEditableFlag(flag = 1)
                        goalViewModel.setGoalEditableFlag(1)
                    }
                    else -> {
                        goalViewModel.updateGoalEditableFlag(flag = 0)
                        goalViewModel.setGoalEditableFlag(0)
                    }
                }
            }
        )
    }
}