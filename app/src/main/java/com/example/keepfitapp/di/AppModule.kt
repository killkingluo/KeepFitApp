package com.example.keepfitapp.di

import android.app.Application
import com.example.keepfitapp.data_source.GoalDao
import com.example.keepfitapp.data_source.KeepFitAppDatabase
import com.example.keepfitapp.data_source.RecordDao
import com.example.keepfitapp.data_source.UserSettingDao
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.RecordRepository
import com.example.keepfitapp.domain.repository.UserSettingRepository
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
    fun provideUserSettingRepository(userSettingDao: UserSettingDao): UserSettingRepository {
        return UserSettingRepository(userSettingDao = userSettingDao)
    }

    @Singleton
    @Provides
    fun provideUserSettingDao(keepFitAppDatabase: KeepFitAppDatabase) : UserSettingDao {
        return keepFitAppDatabase.userSettingDao
    }
}