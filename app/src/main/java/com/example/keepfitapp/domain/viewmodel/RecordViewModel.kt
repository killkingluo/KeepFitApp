package com.example.keepfitapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.keepfitapp.data_source.StepRecord
import com.example.keepfitapp.domain.model.Record
import com.example.keepfitapp.domain.repository.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject

interface RecordViewModelAbstract {
    fun getCurrentSteps(date: Long): Flow<StepRecord>
    fun getRecords(begin_date: Long, end_date: Long): Flow<List<StepRecord>>
    fun getLastDate(): Flow<Long>
    fun updateTargetSteps(target_steps: Int, date: Long)
    fun updateCurrentSteps(current_steps: Int, date: Long)
    fun insertRecord(record: Record)
    fun deleteRecord(record: Record)
}

@HiltViewModel
class RecordViewModel @Inject constructor(private val recordRepository: RecordRepository): ViewModel(), RecordViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun getCurrentSteps(date: Long): Flow<StepRecord> = recordRepository.getCurrentSteps(date)

    override  fun getRecords(begin_date: Long, end_date: Long)  = recordRepository.getRecords(begin_date, end_date)

    override fun getLastDate(): Flow<Long> = recordRepository.getLastDate()

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