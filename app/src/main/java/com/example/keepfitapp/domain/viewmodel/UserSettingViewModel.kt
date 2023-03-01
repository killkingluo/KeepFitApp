package com.example.keepfitapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.example.keepfitapp.domain.repository.UserSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UserSettingViewModelAbstract {
    val goalEditable: Flow<Boolean>
    val historyEditable: Flow<Boolean>
    fun setGoalEditable(goalEditable: Boolean)
    fun setHistoryEditable(historyEditable: Boolean)
}

@HiltViewModel
class UserSettingViewModel @Inject constructor(
    private val userSettingRepository: UserSettingRepository,
) : ViewModel(), UserSettingViewModelAbstract {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    override val goalEditable = userSettingRepository.getGoalEditable()
    override val historyEditable = userSettingRepository.getHistoryEditable()

    override fun setGoalEditable(goalEditable: Boolean) {
        ioScope.launch {userSettingRepository.setGoalEditable(goalEditable)}
    }

    override fun setHistoryEditable(historyEditable: Boolean) {
        ioScope.launch {userSettingRepository.setHistoryEditable(historyEditable)}
    }
}