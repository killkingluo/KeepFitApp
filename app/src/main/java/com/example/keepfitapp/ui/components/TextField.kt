package com.example.keepfitapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldDemo(keyboardType: KeyboardType, textFieldValue :String): String {
    var text by remember{ mutableStateOf(textFieldValue)}
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
    {
        TextField(
            value = text,
            onValueChange = {
                text = it

                            },
            maxLines = 1, //设置行数
            singleLine = true, //单行
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = keyboardType), //设置键盘类型
            keyboardActions = KeyboardActions( onDone = { keyboardController?.hide() } ), //输入完成后隐藏键盘
            shape = RoundedCornerShape(16.dp)
        )
    }
    return text
}