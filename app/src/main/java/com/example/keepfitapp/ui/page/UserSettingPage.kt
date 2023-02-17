package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.SwitchDemo

@Composable
fun SettingsPage(navController: NavController) {
    Row(modifier = Modifier.padding(5.dp)) {
        Text(text = "Goal Editable", modifier = Modifier.align(alignment = Alignment.CenterVertically))
        SwitchDemo()
    }
}