package com.example.keepfitapp.data_source

import androidx.room.*
import com.example.keepfitapp.domain.model.Record
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface RecordDao {
    //获取指定日期的记录
    @Query("SELECT current_steps,target_steps FROM record_table WHERE joined_date = :date")
    fun getCurrentSteps(date: Long): Flow<StepRecord>

    //获取一段时间的记录
    @Query("SELECT current_steps,target_steps FROM record_table WHERE joined_date BETWEEN :begin_date AND :end_date")
    fun getRecords(begin_date: Long, end_date: Long): Flow<List<StepRecord>>

    //获取最后一天的日期
    @Query("SELECT joined_date FROM record_table ORDER BY id DESC LIMIT 1")
    fun getLastDate(): Flow<Long>

    //更新指定日期的目标步数
    @Query("UPDATE record_table SET target_steps = :target_steps WHERE joined_date = :date")
    suspend fun updateTargetSteps(target_steps: Int, date: Long)

    //更新指定日期的当前步数
    @Query("UPDATE record_table SET current_steps = :current_steps WHERE joined_date = :date")
    suspend fun updateCurrentSteps(current_steps: Int, date: Long)

    //冲突策略为替换，插入已存在id的条目时，更新已存在的条目
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)
}


//步数记录类
data class StepRecord(
    @ColumnInfo(name = "current_steps") val current_steps: Int,
    @ColumnInfo(name = "target_steps") val target_steps: Int
)