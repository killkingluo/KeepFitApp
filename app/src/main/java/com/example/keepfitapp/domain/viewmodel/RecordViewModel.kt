package com.example.keepfitapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.keepfitapp.data_source.StepRecord
import com.example.keepfitapp.domain.function.getTodayTimestamp
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RecordViewModelAbstract {
    fun getAllRecords(): Flow<List<Record>>
    fun getCurrentSteps(date: Long): Flow<StepRecord>
    fun getRecords(begin_date: Long, end_date: Long): Flow<List<StepRecord>>
    fun getLastRecord(): Record?
    fun initialization()
    fun updateTargetSteps(target_steps: Int, date: Long)
    fun updateCurrentSteps(current_steps: Int, date: Long)
    fun insertRecord(record: Record)
    fun deleteRecord(record: Record)
    fun deleteAllRecord()
}

@HiltViewModel
class RecordViewModel @Inject constructor(private val recordRepository: RecordRepository, private val goalRepository: GoalRepository): ViewModel(), RecordViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    //当前选择的记录
    private lateinit var currentSelectedRecord: Record

    fun getCurrentSelectedRecord(): Record {
        return currentSelectedRecord
    }

    fun setCurrentSelectedRecord(record: Record){
        currentSelectedRecord = record
    }

    override fun getAllRecords(): Flow<List<Record>> = recordRepository.getAllRecords()
    override fun getCurrentSteps(date: Long): Flow<StepRecord> = recordRepository.getCurrentSteps(date)

    override  fun getRecords(begin_date: Long, end_date: Long)  = recordRepository.getRecords(begin_date, end_date)

    override fun getLastRecord(): Record? = recordRepository.getLastRecord()

    override fun initialization() {
        ioScope.launch {
            val recordNumber = recordRepository.recordCount()
            val todayDate = getTodayTimestamp()
            val lastDate = recordRepository.getLastRecord()?.joined_date
            val activityGoalState = goalRepository.getActivityGoal2()
            if (recordNumber == 0 || (recordNumber > 0 && lastDate != todayDate)) {
                recordRepository.insertRecord(
                    Record(
                        current_steps = 0,
                        target_steps = activityGoalState?.steps ?: 0,
                        joined_date = todayDate
                    )
                )
            }
            //初始化当前选中的记录
            recordRepository.getLastRecord()?.let { setCurrentSelectedRecord(it) }
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

    override fun deleteAllRecord() {
        ioScope.launch { recordRepository.deleteAllRecord() }
    }
}