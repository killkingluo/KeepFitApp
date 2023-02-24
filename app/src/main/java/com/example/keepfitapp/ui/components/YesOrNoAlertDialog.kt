package com.example.keepfitapp.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun YesOrNoAlertDialog(title: String, alertContent: String, onDismiss: () -> Unit, toDO: () -> Unit) {
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = alertContent) },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = toDO
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("No")
                }
            }
        )
}