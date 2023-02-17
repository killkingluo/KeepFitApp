package com.example.keepfitapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SwitchDemo(){
    val isCheck = remember{ mutableStateOf(true) }

        Switch(checked = isCheck.value,
            onCheckedChange = {
                isCheck.value = it
                Log.d("SwitchDemo", if (it) "open" else "close")
            }
        )
}