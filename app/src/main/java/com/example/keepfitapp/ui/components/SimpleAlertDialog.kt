package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.viewmodel.GoalViewModel

@Composable
fun SimpleAlertDialog(title: String, alertContent: String, openDialog: Boolean, onDismiss: () -> Unit) {
    if(openDialog){
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = alertContent) },
            onDismissRequest = onDismiss,
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onDismiss
                    ) {
                        Text("ok")
                    }
                }
            }
        )
    }
}