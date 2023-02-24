package com.example.keepfitapp.ui.components

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SwitchDemo(){
    val isCheck = remember{ mutableStateOf(true) }

        Switch(checked = isCheck.value,
            onCheckedChange = { isCheck.value = it }
        )
}