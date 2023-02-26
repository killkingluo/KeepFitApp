package com.example.keepfitapp.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.model.Record

@Database(entities = [Goal::class, Record::class], version = 1, exportSchema = false)
abstract class KeepFitAppDatabase: RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val recordDao: RecordDao

    //初始化db
    companion object {
        private var INSTANCE: KeepFitAppDatabase? = null
        fun getInstance(context: Context): KeepFitAppDatabase{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, KeepFitAppDatabase::class.java, "keepFitApp_db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as KeepFitAppDatabase
        }
    }
}