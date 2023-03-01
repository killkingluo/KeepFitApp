package com.example.keepfitapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keepfitapp.domain.function.timeStampToDate
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.model.Screen
import com.example.keepfitapp.domain.viewmodel.RecordViewModel
import com.example.keepfitapp.domain.viewmodel.UserSettingViewModel

@Composable
fun CardDemo(steps: Int, cardName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append(cardName)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 40.sp,
                                letterSpacing = 1.sp,
                                color = Color(0xFF4552B8)
                            )
                        ) {
                            append(steps.toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append(" Steps")
                        }
                    }
                )
            }
        }
    }
}

//For history page to use
@Composable
fun HistoryCardDemo(record: Record, navController: NavController, recordViewModel: RecordViewModel, userSettingViewModel: UserSettingViewModel) {
    val historyEditable = userSettingViewModel.historyEditable.collectAsState(initial = false)
    val percentage: Float = if (record.target_steps == 0) 0F
    else if (record.current_steps > record.target_steps) {
        1F
    }
    else {
        record.current_steps.toFloat() / record.target_steps.toFloat()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(weight = 1f, fill = false),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp,
                                color = Color(0xFF4552B8)
                            )
                        )
                        {
                            append(record.current_steps.toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append("/")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp,
                                color = Color(0xFF4552B8)
                            )
                        ) {
                            append(record.target_steps.toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append(" Steps")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                fontSize = 20.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        {
                            append(" ${String.format("%.2f", record.current_steps.toFloat() / record.target_steps.toFloat() * 100)}%")
                        }
                    }
                )
                    Canvas(
                        modifier = Modifier.width(300.dp)
                            .height(15.dp)
                    ) {
                        drawRoundRect(
                            color = Color.LightGray,
                            size = size,
                            cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                        )
                        drawRoundRect(
                            color = Color(0xFF4552B8),
                            size = Size(width = this.size.width * percentage, height = this.size.height),
                            cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                        )
                    }
                Text(
                    text = timeStampToDate(record.joined_date),
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp
                )
            }
            if(historyEditable.value) {
                IconButton(
                    onClick = {
                        recordViewModel.setCurrentSelectedRecord(record)
                        navController.navigate(route = Screen.EditRecord.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        tint = Color.Black,
                        contentDescription = null)
                }
            }
        }
    }
}

