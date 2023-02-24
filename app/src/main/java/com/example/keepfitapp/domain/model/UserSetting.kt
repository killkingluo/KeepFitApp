package com.example.keepfitapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_setting_table")
data class UserSetting (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goal_editable_flag: Int  //0代表不可编辑，1代表可以编辑
)