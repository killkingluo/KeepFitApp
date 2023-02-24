package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.ui.theme.Purple500

@Composable
fun SimpleAlertDialog(title: String, alertContent: String,onDismiss: () -> Unit) {
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = alertContent) },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("OK")
                }
            }
        )
}