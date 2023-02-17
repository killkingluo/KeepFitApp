package com.example.keepfitapp.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keepfitapp.domain.model.Goal

@Database(entities = [Goal::class], version = 1, exportSchema = false)
abstract class GoalDatabase: RoomDatabase() {
    abstract val goalDao: GoalDao

    //初始化db
    companion object {
        private var INSTANCE: GoalDatabase? = null
        fun getInstance(context: Context): GoalDatabase{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, GoalDatabase::class.java, "goal_table")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as GoalDatabase
        }
    }
}