package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel

@Composable
fun SettingsPage(userSettingViewModel: UserSettingViewModel) {
    val isCheck = userSettingViewModel.goalEditable.collectAsState(initial = false)

    Row(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Goal Editable",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(Modifier.weight(1f))
        Switch(checked = isCheck.value,
            onCheckedChange = {
                userSettingViewModel.setUserSetting(it)
            }
        )

    }
}