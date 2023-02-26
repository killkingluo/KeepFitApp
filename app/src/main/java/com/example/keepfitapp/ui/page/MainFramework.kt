package com.example.keepfitapp.ui.page

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfitapp.*
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel
import com.example.keepfitapp.ui.theme.Blue700


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFramework(goalViewModel: GoalViewModel,recordViewModel: RecordViewModel, userSettingViewModel: UserSettingViewModel) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navigationItems = listOf(Screen.Home, Screen.History)

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(5.dp),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                modifier= Modifier.fillMaxWidth()
            ) {
                Row(modifier= Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) { Icon(Icons.Filled.ArrowBack, null) }
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Settings.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) { Icon(Icons.Filled.Settings, null) }
                }
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        icon = {
                            when(index){
                                0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                else -> Icon(Icons.Filled.Info, contentDescription = null)
                            }
                        },
                        label = { Text(stringResource(navigationItem.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                        selectedContentColor =  Blue700,
                        unselectedContentColor =  Color.Gray,
                        onClick = { navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                        }
                    )
                }
            }

        },
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
            //.padding(it) // <<-- or simply this
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) {
                    HomePage(navController = navController, recordViewModel = recordViewModel, goalViewModel = goalViewModel)
                }
                composable(Screen.History.route) {
                    HistoryPage(navController = navController, recordViewModel = recordViewModel, goalViewModel = goalViewModel)
                }
                composable(Screen.Settings.route) {
                    SettingsPage(userSettingViewModel = userSettingViewModel)
                }
                composable(Screen.LogSteps.route) {
                    LogStepsPage(navController = navController, recordViewModel = recordViewModel)
                }
                composable(route = Screen.GoalSetting.route) {
                    GoalSetPage(navController = navController, goalViewModel = goalViewModel, recordViewModel = recordViewModel, userSettingViewModel = userSettingViewModel)
                }
                composable(Screen.GoalAdd.route) {
                    GoalAddPage(navController = navController,goalViewModel = goalViewModel)
                }
                composable(Screen.EditRecord.route) {
                    EditRecordPage(navController = navController,goalViewModel = goalViewModel, recordViewModel = recordViewModel)
                }
            }
        }
    }
}
