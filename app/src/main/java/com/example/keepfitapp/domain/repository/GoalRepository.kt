package com.example.keepfitapp.domain.repository

import com.example.keepfitapp.data_source.GoalDao
import com.example.keepfitapp.domain.model.Goal
import kotlinx.coroutines.flow.Flow

class GoalRepository(private val goalDao: GoalDao) {
    fun getAllGoals(): Flow<List<Goal>> = goalDao.getAllGoals()

    fun getGoalById(id: Int): Flow<Goal> = goalDao.getGoalById(id)

    fun getActivityGoal(): Flow<Goal> = goalDao.getActivityGoal()

    suspend fun cancelActivityGoal() = goalDao.cancelActivityGoal()

    suspend fun newActivityGoal(id: Int) = goalDao.newActivityGoal(id)

    suspend fun insert(goal: Goal) = goalDao.insertGoal(goal)

    suspend fun delete(goal: Goal) = goalDao.deleteGoal(goal)
}