package com.example.keepfitapp.domain.repository

import com.example.keepfitapp.data_source.RecordDao
import com.example.keepfitapp.data_source.StepRecord
import com.example.keepfitapp.domain.model.Record
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordDao: RecordDao) {
    fun getCurrentSteps(date: Long): Flow<StepRecord> = recordDao.getCurrentSteps(date)

    fun getRecords(begin_date: Long, end_date: Long): Flow<List<StepRecord>> = recordDao.getRecords(begin_date, end_date)

    fun getLastDate(): Long = recordDao.getLastDate()

    fun recordCount(): Int = recordDao.recordCount()

    suspend fun updateTargetSteps(target_steps: Int, date: Long) = recordDao.updateTargetSteps(target_steps, date)

    suspend fun updateCurrentSteps(current_steps: Int, date: Long) = recordDao.updateCurrentSteps(current_steps, date)

    suspend fun insertRecord(record: Record) = recordDao.insertRecord(record)

    suspend fun deleteRecord(record: Record) = recordDao.deleteRecord(record)
}