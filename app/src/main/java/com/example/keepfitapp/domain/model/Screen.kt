package com.example.keepfitapp.domain.model

import androidx.annotation.StringRes
import com.example.keepfitapp.R

//导航实体类
sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("Home", R.string.Home)
    object History : Screen("History", R.string.History)
    object Settings : Screen("Settings", R.string.Settings)
    object LogSteps : Screen("LogSteps", R.string.LogSteps)
    object GoalSetting : Screen("GoalSetting", R.string.GoalSetting)
    object GoalAdd : Screen("GoalAdd", R.string.GoalAdd)

    object EditRecord : Screen("EditRecord", R.string.GoalAdd)
}