package com.example.keepfitapp.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.keepfitapp.domain.model.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class RecordDatabase: RoomDatabase() {
    abstract val recordDao: RecordDao

    //初始化db
    companion object {
        private var INSTANCE: RecordDatabase? = null
        fun getInstance(context: Context): RecordDatabase{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, RecordDatabase::class.java, "record_table")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as RecordDatabase
        }
    }
}