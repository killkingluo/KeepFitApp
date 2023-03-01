package com.example.keepfitapp.domain.repository


import com.example.keepfitapp.data_source.DateStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSettingRepository @Inject constructor(private val dateStoreManager: DateStoreManager) {

    suspend fun setGoalEditable(goalEditable: Boolean) {
        dateStoreManager.setGoalEditable(goalEditable)
    }

    suspend fun setHistoryEditable(historyEditable: Boolean) {
        dateStoreManager.setHistoryEditable(historyEditable)
    }

    fun getGoalEditable(): Flow<Boolean> {
        return dateStoreManager.getGoalEditable()
    }

    fun getHistoryEditable(): Flow<Boolean> {
        return dateStoreManager.getHistoryEditable()
    }
}