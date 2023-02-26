package com.example.keepfitapp.domain.repository


import com.example.keepfitapp.data_source.DateStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSettingRepository @Inject constructor(private val dateStoreManager: DateStoreManager) {

    suspend fun setUserSetting(goalEditable: Boolean) {
        dateStoreManager.setGoalEditable(goalEditable)
    }

    fun getUserSetting(): Flow<Boolean> {
        return dateStoreManager.getGoalEditable()
    }

}