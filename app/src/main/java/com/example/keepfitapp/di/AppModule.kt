package com.example.keepfitapp.di

import android.app.Application
import android.content.Context

import com.example.keepfitapp.data_source.*
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.RecordRepository
import com.example.keepfitapp.domain.repository.UserSettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideKeepFitAppDatabase(app: Application) : KeepFitAppDatabase{
        return KeepFitAppDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepository(goalDao = goalDao)
    }

    @Singleton
    @Provides
    fun provideGoalDao(keepFitAppDatabase: KeepFitAppDatabase) : GoalDao{
        return keepFitAppDatabase.goalDao
    }

    @Singleton
    @Provides
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository {
        return RecordRepository(recordDao = recordDao)
    }

    @Singleton
    @Provides
    fun provideRecordDao(keepFitAppDatabase: KeepFitAppDatabase) : RecordDao{
        return keepFitAppDatabase.recordDao
    }

    @Singleton
    @Provides
    fun provideUserSettingRepository(dateStoreManager: DateStoreManager): UserSettingRepository {
        return UserSettingRepository(dateStoreManager)
    }

    @Singleton
    @Provides
    fun provideDateStoreManager(@ApplicationContext context: Context): DateStoreManager {
        return DateStoreManager(context)
    }
}