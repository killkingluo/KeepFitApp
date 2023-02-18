package com.example.keepfitapp.data_source


import androidx.room.*
import com.example.keepfitapp.domain.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal_table ORDER BY id ASC")
    fun getAllGoals(): Flow<List<Goal>>

    @Query("SELECT * FROM goal_table WHERE id = :id")
    fun getGoalById(id: Int): Flow<Goal>

    //获取当前激活的目标
    @Query("SELECT * FROM goal_table WHERE activityFlag = 1 LIMIT 1")
    fun getActivityGoal(): Flow<Goal>

    //清除当前激活的目标
    @Query("UPDATE goal_table SET activityFlag = 0 WHERE activityFlag = 1")
    suspend fun cancelActivityGoal()

    //激活目标
    @Query("UPDATE goal_table SET activityFlag = 1 WHERE id = :id")
    suspend fun newActivityGoal(id: Int)

    //冲突策略为替换，插入已存在id的条目时，更新已存在的条目
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)
}
