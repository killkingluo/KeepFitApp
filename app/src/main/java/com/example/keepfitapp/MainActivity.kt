package com.example.keepfitapp

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.keepfitapp.domain.viewmodel.GoalViewModel
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.ui.page.*
import com.example.keepfitapp.ui.theme.KeepFitAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val goalViewModel: GoalViewModel by viewModels()
        val recordViewModel: RecordViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            KeepFitAppTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    MainFramework(goalViewModel = goalViewModel,recordViewModel = recordViewModel)
                }
            }
        }
    }
}

