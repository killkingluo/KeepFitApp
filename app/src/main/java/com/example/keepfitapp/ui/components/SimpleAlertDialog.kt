package com.example.keepfitapp.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable

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