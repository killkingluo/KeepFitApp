package com.example.keepfitapp.domain.repository


import com.example.keepfitapp.data_source.UserSettingDao
import com.example.keepfitapp.domain.model.UserSetting
import javax.inject.Inject

class UserSettingRepository @Inject constructor(private val userSettingDao: UserSettingDao) {

    fun getGoalEditableFlag(): Int?  = userSettingDao.getGoalEditableFlag()

    fun dataCheck(): Int = userSettingDao.dataCheck()

    suspend fun updateGoalEditableFlag(flag: Int) = userSettingDao.updateGoalEditableFlag(flag)

    suspend fun insertFlag(goalEditableFlag: UserSetting) = userSettingDao.insertFlag(goalEditableFlag)

    suspend fun deleteFlag(goalEditableFlag: UserSetting) = userSettingDao.deleteFlag(goalEditableFlag)
}