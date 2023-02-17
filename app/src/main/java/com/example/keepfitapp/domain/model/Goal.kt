package com.example.keepfitapp.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goal_table")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val steps: Int,
    val activityFlag : Int = 0 //0表示未激活，1表示激活
)
