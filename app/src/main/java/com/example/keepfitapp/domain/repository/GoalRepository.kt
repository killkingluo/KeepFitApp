package com.example.keepfitapp.domain.repository

import com.example.keepfitapp.data_source.GoalDao
import com.example.keepfitapp.domain.model.Goal
import kotlinx.coroutines.flow.Flow

class GoalRepository(private val goalDao: GoalDao) {
    fun getAllGoals(): Flow<List<Goal>> = goalDao.getAllGoals()

    fun getGoalById(id: Int): Flow<Goal> = goalDao.getGoalById(id)

    fun getActivityGoal(): Flow<Goal> = goalDao.getActivityGoal()

    fun getActivityGoal2(): Goal? = goalDao.getActivityGoal2()

    suspend fun cancelActivityGoal() = goalDao.cancelActivityGoal()

    suspend fun newActivityGoal(id: Int) = goalDao.newActivityGoal(id)

    suspend fun insertGoal(goal: Goal) = goalDao.insertGoal(goal)

    suspend fun deleteGoal(goal: Goal) = goalDao.deleteGoal(goal)
}