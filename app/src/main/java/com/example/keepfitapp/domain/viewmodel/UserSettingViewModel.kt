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
    fun setUserSetting(goalEditable: Boolean)
}

@HiltViewModel
class UserSettingViewModel @Inject constructor(
    private val userSettingRepository: UserSettingRepository,
) : ViewModel(), UserSettingViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    override val goalEditable = userSettingRepository.getUserSetting()

    override fun setUserSetting(goalEditable: Boolean) {
        ioScope.launch {userSettingRepository.setUserSetting(goalEditable)}
    }
}