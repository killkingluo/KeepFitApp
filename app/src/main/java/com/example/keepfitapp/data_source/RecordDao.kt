package com.example.keepfitapp.data_source

import androidx.room.*
import com.example.keepfitapp.domain.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    //获取全部记录
    @Query("SELECT * FROM record_table ORDER BY joined_date DESC")
    fun getAllRecords(): Flow<List<Record>>

    //获取指定日期的记录flow
    @Query("SELECT * FROM record_table WHERE joined_date = :date LIMIT 1")
    fun getCurrentRecord(date: Long): Flow<Record>

    //获取指定日期的记录
    @Query("SELECT * FROM record_table WHERE joined_date = :date LIMIT 1")
    fun getSelectDateRecord(date: Long): Record?

    //获取一段时间的记录
    @Query("SELECT * FROM record_table WHERE joined_date BETWEEN :begin_date AND :end_date ORDER BY joined_date DESC")
    fun getRecords(begin_date: Long, end_date: Long): Flow<List<Record>>

    //获取最后一天的日期
    @Query("SELECT * FROM record_table ORDER BY joined_date DESC LIMIT 1")
    fun getLastRecord(): Record?

    //检查是否有数据
    @Query("SELECT COUNT(*) FROM record_table")
    fun recordCount(): Int

    //更新指定日期的目标步数
    @Query("UPDATE record_table SET target_steps = :target_steps WHERE joined_date = :date")
    suspend fun updateTargetSteps(target_steps: Int, date: Long)

    //更新指定日期的当前步数
    @Query("UPDATE record_table SET current_steps = :current_steps WHERE joined_date = :date")
    suspend fun updateCurrentSteps(current_steps: Int, date: Long)

    //冲突策略为替换，插入已存在id的条目时，更新已存在的条目
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record)

    @Update
    suspend fun updateRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)

    @Query("DELETE FROM record_table")
    suspend fun deleteAllRecord()
}
