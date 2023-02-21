package com.example.keepfitapp.ui.page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.keepfitapp.R
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.components.GoalCardDemo

@SuppressLint("SuspiciousIndentation")
@Composable
fun GoalSetPage(navController: NavController, goalViewModel: GoalViewModel, recordViewModel: RecordViewModel) {
    val goalListState = goalViewModel.getAllGoals.collectAsState(initial = listOf())
    Column(modifier = Modifier.padding(5.dp).fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(goalListState.value.size) { index ->
            val goal = goalListState.value[index]
                GoalCardDemo(goal = goal, goalViewModel = goalViewModel, recordViewModel = recordViewModel)
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(Screen.GoalAdd.route) } ) {
                Text(text = stringResource(id = R.string.goal_add_button))
            }
        }
    }
}

