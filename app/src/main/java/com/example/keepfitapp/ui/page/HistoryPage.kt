package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.ButtonDemo
import com.example.keepfitapp.CardDemo

@Composable
fun HistoryPage(navController: NavController) {
    Column(modifier = Modifier.padding(5.dp)) {
        CardDemo(4090, "Weekly   ")
        CardDemo(5090, "Last week   ")
        CardDemo(1000, "7 day AVG   ")
        ButtonDemo("Edit History", navController, "History")
        ButtonDemo("Delete All History", navController, "History")
    }
}