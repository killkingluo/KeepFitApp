package com.example.keepfitapp.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun yesOrNoAlertDialog(title: String, alertContent: String, openDialog: Boolean, onDismiss: () -> Unit, toDO: () -> Unit) {
    if(openDialog){
        AlertDialog(
            title = { Text(text = title) },
            text = { Text(text = alertContent) },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = toDO
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}