package com.example.keepfitapp.di

import android.app.Application
import com.example.keepfitapp.data_source.GoalDao
import com.example.keepfitapp.data_source.GoalDatabase
import com.example.keepfitapp.data_source.RecordDao
import com.example.keepfitapp.data_source.RecordDatabase
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.RecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepository(goalDao = goalDao)
    }

    @Singleton
    @Provides
    fun provideGoalDatabase(app: Application) : GoalDatabase{
        return GoalDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideGoalDao(goalDatabase: GoalDatabase) : GoalDao{
        return goalDatabase.goalDao
    }

    @Singleton
    @Provides
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository {
        return RecordRepository(recordDao = recordDao)
    }

    @Singleton
    @Provides
    fun provideRecordDatabase(app: Application) : RecordDatabase{
        return RecordDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideRecordDao(recordDatabase: RecordDatabase) : RecordDao{
        return recordDatabase.recordDao
    }
}