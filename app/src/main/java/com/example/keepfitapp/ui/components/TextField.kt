package com.example.keepfitapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.keepfitapp.domain.function.inputCheck

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun textFieldDemo(
    keyboardType: KeyboardType,
    textFieldValue: String,
    checkType: Int,
    labelId: String,
    contentDescription: String
): String {
    var text by remember { mutableStateOf(textFieldValue) }
    var isError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    //0 input number
    //1 input string
    val regex: String = if (checkType == 0) "^\\d{1,7}\$" else "^[a-zA-Z].*"
    val errorMessage =
        if (checkType == 0) "Please enter number and no more than 9999999"
        else "Please enter a string beginning with a letter"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Surface(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)

        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    isError = inputCheck(text = text, regex = regex)
                },
                label = { Text(text = labelId) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = contentDescription
                    )
                },
                trailingIcon = {
                    if (isError)
                        Icon(Icons.Filled.Info, contentDescription = "error", tint = MaterialTheme.colors.error)
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                enabled = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }), //输入完成后隐藏键盘
            )
        }
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
            )
        }
    }
    return text
}