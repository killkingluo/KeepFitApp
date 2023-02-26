package com.example.keepfitapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.keepfitapp.domain.function.inputCheck

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun textFieldDemo(keyboardType: KeyboardType, textFieldValue: String, checkType: Int): String {
    var text by remember{ mutableStateOf(textFieldValue)}
    var isError by remember{ mutableStateOf(false)}
    val keyboardController = LocalSoftwareKeyboardController.current
    //0 input number
    //1 input string
    val regex: String = if(checkType == 0) "^\\d{1,7}\$" else "^[a-zA-Z].*"
    val errorMessage =
        if(checkType == 0) "Please enter number and no more than 9999999"
        else "Please enter a string beginning with a letter"


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(
            value = text,
            onValueChange = {
                text = it
                isError = inputCheck(text = text, regex = regex)
                            },
            maxLines = 1, //设置行数
            singleLine = true, //单行
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = keyboardType
            ), //设置键盘类型
            keyboardActions = KeyboardActions( onDone = { keyboardController?.hide() } ), //输入完成后隐藏键盘
            shape = RoundedCornerShape(16.dp)
        )
        if(isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
            )
        }
    }
    return text
}