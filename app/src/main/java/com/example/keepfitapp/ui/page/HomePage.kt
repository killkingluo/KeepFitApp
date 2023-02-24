package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.ButtonDemo
import com.example.keepfitapp.CardDemo
import com.example.keepfitapp.TextCardDemo
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel

@Composable
fun HomePage(navController: NavController, goalViewModel : GoalViewModel, recordViewModel: RecordViewModel) {
    val todayDate = getTodayTimestamp()
    val activityGoalState = goalViewModel.getActivityGoal().collectAsState(initial = null).value
    val currentRecordState = recordViewModel.getCurrentRecord(todayDate).collectAsState(initial = null).value
    val currentRemainSteps = currentRecordState?.target_steps?.minus(currentRecordState.current_steps)?: 0

    recordViewModel.initialization() //检查：如果不存在今天的记录，新增记录
    goalViewModel.initialization() //检查：是否需要初始化用户设置

    Column(modifier = Modifier.padding(5.dp)) {
        //如果不存在激活的目标，提示去激活目标
        if (activityGoalState == null) {
            TextCardDemo(text = "Please activity a goal first!")
        }

        CardDemo(steps = currentRecordState?.target_steps ?: 0, cardName = "Goal   ")
        CardDemo(steps = currentRecordState?.current_steps ?: 0, cardName = "Current   ")
        CardDemo(steps = currentRemainSteps, cardName = "Remaining   ")
        ButtonDemo(buttonName = "LOG", navController = navController, destinationPage = Screen.LogSteps.route)
        ButtonDemo(buttonName = "Goal Setting",navController = navController, destinationPage = Screen.GoalSetting.route)
    }
}