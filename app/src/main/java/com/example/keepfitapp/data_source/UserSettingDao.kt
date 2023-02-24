package com.example.keepfitapp.data_source

import androidx.room.*
import com.example.keepfitapp.domain.model.UserSetting

@Dao
interface UserSettingDao {
    @Query("SELECT goal_editable_flag FROM user_setting_table LIMIT 1")
    fun getGoalEditableFlag(): Int?

    @Query("SELECT COUNT(*) FROM user_setting_table")
    fun dataCheck(): Int

    @Query("UPDATE user_setting_table SET goal_editable_flag = :flag")
    suspend fun updateGoalEditableFlag(flag: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlag(goalEditableFlag: UserSetting)

    @Delete
    suspend fun deleteFlag(goalEditableFlag: UserSetting)
}