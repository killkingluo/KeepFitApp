package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.components.*
import com.example.keepfitapp.ui.theme.Purple500

@Composable
fun HomePage(navController: NavController, goalViewModel : GoalViewModel, recordViewModel: RecordViewModel) {
    val todayDate = getTodayTimestamp()
    val activityGoalState = goalViewModel.getActivityGoal().collectAsState(initial = null).value
    val currentRecordState = recordViewModel.getCurrentRecord(todayDate).collectAsState(initial = null).value
    var currentRemainSteps = currentRecordState?.target_steps?.minus(currentRecordState.current_steps)?: 0

    recordViewModel.initialization() //检查：如果不存在今天的记录，新增记录

    if(currentRemainSteps < 0) {
        currentRemainSteps = 0
    }
    Column(
        modifier = Modifier.padding(5.dp).verticalScroll(rememberScrollState())
    ) {
        //如果不存在激活的目标，提示去激活目标
        if (activityGoalState == null) {
            TextCardDemo(text = "Please activity a goal first!")
        }

        GoalBar(GoalSteps = currentRecordState?.target_steps ?: 0)
        CurrentBar(currentSteps = currentRecordState?.current_steps ?: 0)
        RemainBar(remainingSteps = currentRemainSteps)
        HomePageBarChart(goalSteps = currentRecordState?.target_steps ?: 0, currentSteps = currentRecordState?.current_steps ?: 0)
        FloatingActionButton(
            modifier = Modifier.size(width = 50.dp,height = 50.dp)
            .align(Alignment.CenterHorizontally),
            backgroundColor = Color(0xFF4552B8),
            onClick = { navController.navigate(Screen.LogSteps.route) }
        ) {
            Icon(
                modifier = Modifier.size(width = 45.dp,height = 45.dp),
                imageVector = Icons.Filled.Add,
                tint = Color.White,
                contentDescription = "Localized description")
        }

    }
}