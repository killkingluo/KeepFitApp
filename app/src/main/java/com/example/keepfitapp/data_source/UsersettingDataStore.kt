package com.example.keepfitapp.data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DateStoreManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val dataStore = context.dataStore

    companion object{
        val goalEditableKey = booleanPreferencesKey(name = "GOAL_EDITABLE_KEY")
        val historyEditableKey = booleanPreferencesKey(name = "HISTORY_EDITABLE_KEY")
    }

    suspend fun setGoalEditable(isEditable: Boolean) {
        dataStore.edit { pref ->
            pref[goalEditableKey] = isEditable
        }
    }

    suspend fun setHistoryEditable(isEditable: Boolean) {
        dataStore.edit { pref ->
            pref[historyEditableKey] = isEditable
        }
    }

    fun getGoalEditable(): Flow<Boolean> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                }
                else {
                    throw exception
                }
            }
            .map { pref ->
                val goalEditable = pref[goalEditableKey] ?:false
                goalEditable
            }
    }

    fun getHistoryEditable(): Flow<Boolean> {
        return dataStore.data
            .catch {exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                }
                else {
                    throw exception
                }
            }
            .map { pref ->
                val historyEditable = pref[historyEditableKey] ?:false
                historyEditable
            }
    }
}