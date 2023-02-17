package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.ButtonDemo
import com.example.keepfitapp.CardDemo
import com.example.keepfitapp.TextFieldDemo


@Composable
fun LogStepsPage(navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        CardDemo(2000, "Current   ")
        TextFieldDemo(KeyboardType.Number)
        ButtonDemo("Submit", navController, "Home")
    }
}