package com.example.keepfitapp.domain.repository

import com.example.keepfitapp.data_source.RecordDao
import com.example.keepfitapp.domain.model.Record
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecordRepository @Inject constructor(private val recordDao: RecordDao) {
    fun getAllRecords(): Flow<List<Record>> = recordDao.getAllRecords()

    fun getCurrentRecord(date: Long): Flow<Record> = recordDao.getCurrentRecord(date)

    fun getSelectDateRecord(date: Long): Record? = recordDao.getSelectDateRecord(date)

    fun getRecords(begin_date: Long, end_date: Long): Flow<List<Record>> = recordDao.getRecords(begin_date, end_date)

    fun getLastRecord(): Record? = recordDao.getLastRecord()

    fun recordCount(): Int = recordDao.recordCount()

    suspend fun updateTargetSteps(target_steps: Int, date: Long) = recordDao.updateTargetSteps(target_steps, date)

    suspend fun updateCurrentSteps(current_steps: Int, date: Long) = recordDao.updateCurrentSteps(current_steps, date)

    suspend fun updateRecord(record: Record) = recordDao.updateRecord(record)

    suspend fun insertRecord(record: Record) = recordDao.insertRecord(record)

    suspend fun deleteRecord(record: Record) = recordDao.deleteRecord(record)

    suspend fun deleteAllRecord() = recordDao.deleteAllRecord()
}