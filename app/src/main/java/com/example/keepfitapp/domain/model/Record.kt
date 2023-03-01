package com.example.keepfitapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "record_table")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val current_steps: Int,
    val target_steps:Int,
    val joined_date: Long,
)
