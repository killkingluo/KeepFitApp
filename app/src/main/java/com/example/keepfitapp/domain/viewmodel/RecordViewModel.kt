package com.example.keepfitapp.domain.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.keepfitapp.data_source.StepRecord
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.RecordRepository
import com.example.keepfitapp.ui.page.getTodayTimestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import javax.inject.Inject

interface RecordViewModelAbstract {
    fun getCurrentSteps(date: Long): Flow<StepRecord>
    fun getRecords(begin_date: Long, end_date: Long): Flow<List<StepRecord>>
    fun getLastDate(): Long
    fun recordCountAndCheck()
    fun updateTargetSteps(target_steps: Int, date: Long)
    fun updateCurrentSteps(current_steps: Int, date: Long)
    fun insertRecord(record: Record)
    fun deleteRecord(record: Record)
}

@HiltViewModel
class RecordViewModel @Inject constructor(private val recordRepository: RecordRepository, private val goalRepository: GoalRepository): ViewModel(), RecordViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun getCurrentSteps(date: Long): Flow<StepRecord> = recordRepository.getCurrentSteps(date)

    override  fun getRecords(begin_date: Long, end_date: Long)  = recordRepository.getRecords(begin_date, end_date)

    override fun getLastDate(): Long = recordRepository.getLastDate()

    override fun recordCountAndCheck() {
        ioScope.launch {
            val recordNumber = recordRepository.recordCount()
            val todayDate = getTodayTimestamp()
            val lastDate = recordRepository.getLastDate()
            val activityGoalState = goalRepository.getActivityGoal2()
                if(recordNumber == 0 || (recordNumber > 0 && lastDate != todayDate)) {
                    recordRepository.insertRecord(
                        Record(
                            current_steps = 0,
                            target_steps = activityGoalState?.steps ?: 0,
                            joined_date = todayDate
                        )
                    )
                }
        }
    }

    override fun updateTargetSteps(target_steps: Int, date: Long) {
        ioScope.launch { recordRepository.updateTargetSteps(target_steps, date) }
    }

    override fun updateCurrentSteps(current_steps: Int, date: Long) {
        ioScope.launch { recordRepository.updateCurrentSteps(current_steps, date) }
    }

    override fun insertRecord(record: Record) {
        ioScope.launch { recordRepository.insertRecord(record) }
    }

    override fun deleteRecord(record: Record) {
        ioScope.launch { recordRepository.deleteRecord(record) }
    }
}