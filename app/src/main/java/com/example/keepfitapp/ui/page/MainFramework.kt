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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val settingSelected = remember { mutableStateOf(false) }
    val currentDestination = navBackStackEntry?.destination
    val navigationItems = listOf(Screen.Home, Screen.Goal, Screen.History)

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(5.dp),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                modifier= Modifier.fillMaxWidth()
            ) {
                Row(modifier= Modifier.fillMaxWidth()) {
                    if(currentDestination?.route != Screen.Home.route) {
                        IconButton(
                            onClick = { navController.navigateUp() }
                        ) { Icon(Icons.Filled.ArrowBack, null) }
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Settings.route)
                        }
                    ) { Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    ) }
                }
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        icon = {
                            when(index){
                                0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                1 -> Icon(Icons.Filled.CheckCircle, contentDescription = null)
                                else -> Icon(Icons.Filled.DateRange, contentDescription = null)
                            }
                        },
                        label = { Text(stringResource(navigationItem.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                        selectedContentColor =  Color(0xFF4552B8),
                        unselectedContentColor =  Color.Gray,
                        onClick = { navController.navigate(navigationItem.route) {
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
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(Screen.Home.route) {
                    HomePage(navController = navController, recordViewModel = recordViewModel, goalViewModel = goalViewModel)
                }
                composable(Screen.History.route) {
                    HistoryPage(navController = navController, recordViewModel = recordViewModel, userSettingViewModel = userSettingViewModel)
                }
                composable(Screen.Settings.route) {
                    SettingsPage(userSettingViewModel = userSettingViewModel)
                }
                composable(Screen.LogSteps.route) {
                    LogStepsPage(navController = navController, recordViewModel = recordViewModel)
                }
                composable(route = Screen.Goal.route) {
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
