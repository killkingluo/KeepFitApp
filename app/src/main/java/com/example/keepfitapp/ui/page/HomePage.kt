package com.example.keepfitapp.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keepfitapp.ButtonDemo
import com.example.keepfitapp.CardDemo
import com.example.keepfitapp.TextCardDemo
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomePage(navController: NavController, goalViewModel : GoalViewModel, recordViewModel: RecordViewModel) {
    val todayDate = getTodayTimestamp()
    //val lastDate = recordViewModel.getLastDate().collectAsState(initial = null).value
    //val recordNumber = recordViewModel.recordCount().collectAsState(initial = null).value
    val activityGoalState = goalViewModel.getActivityGoal().collectAsState(initial = null).value
    val currentRecordState = recordViewModel.getCurrentSteps(todayDate).collectAsState(initial = null).value
    val currentRemainSteps = currentRecordState?.target_steps?.minus(currentRecordState.current_steps)?: 0
    Column(modifier = Modifier.padding(5.dp)) {
        //如果不存在激活的目标，提示去激活目标
        if (activityGoalState == null) {
            TextCardDemo(text = "Please activity a goal first!")
        }
        //如果不存在今天的记录，新增记录
        recordViewModel.recordCountAndCheck()
//        if(recordNumber != null) {
//            if(recordNumber == 0 || (recordNumber > 0 && lastDate != null && lastDate!=todayDate)) {
//                recordViewModel.insertRecord(
//                    Record(
//                        current_steps = 0,
//                        target_steps = activityGoalState?.steps ?: 0,
//                        joined_date = todayDate
//                    )
//                )
//            }
//        }
        CardDemo(steps = currentRecordState?.target_steps ?: 0, cardName = "Goal   ")
        CardDemo(steps = currentRecordState?.current_steps ?: 0, cardName = "Current   ")
        CardDemo(steps = currentRemainSteps, cardName = "Remaining   ")
        ButtonDemo(buttonName = "LOG", navController = navController, destinationPage = "LogSteps")
        ButtonDemo(buttonName = "Goal Setting",navController = navController, destinationPage = "GoalSetting")
    }
}

//获取今天日期的时间戳，去掉分秒
fun getTodayTimestamp(): Long {
    return SimpleDateFormat("yyyy-MM-dd").parse(SimpleDateFormat("yyyy-MM-dd").format(Date().time))!!.time
}