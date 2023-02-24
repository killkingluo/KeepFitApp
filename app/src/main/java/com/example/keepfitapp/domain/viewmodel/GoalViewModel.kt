package com.example.keepfitapp.domain.viewmodel


import androidx.lifecycle.*
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.model.UserSetting
import com.example.keepfitapp.domain.repository.GoalRepository
import com.example.keepfitapp.domain.repository.UserSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GoalViewModelAbstract {
    val getAllGoals: Flow<List<Goal>>

    fun initialization()
    fun getGoalById(id: Int): Flow<Goal>
    fun getActivityGoal(): Flow<Goal>
    fun cancelActivityGoal()
    fun newActivityGoal(id: Int)
    fun insertGoal(goal: Goal)
    fun deleteGoal(goal: Goal)
    fun updateGoalEditableFlag(flag: Int)
    fun insertGoalEditableFlag(goalEditableFlag: UserSetting)
    fun deleteGoalEditableFlag(goalEditableFlag: UserSetting)
}

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val userSettingRepository: UserSettingRepository
) : ViewModel(), GoalViewModelAbstract {

    override val getAllGoals: Flow<List<Goal>> = goalRepository.getAllGoals()
    private lateinit var currentSelectGoal: Goal
    private var goalEditableFlag: Int = 0

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun getCurrentSelectGoal(): Goal {
        return currentSelectGoal
    }

    fun setCurrentSelectGoal(goal: Goal?){
        if (goal != null) {
            currentSelectGoal = goal
        }
    }

    fun getGoalEditableFlag(): Int {
        return goalEditableFlag
    }

    fun setGoalEditableFlag(flag: Int){
        goalEditableFlag = flag
    }

    //初始化目标是否可编辑设置
    override fun initialization() {
        ioScope.launch {
            if(userSettingRepository.dataCheck() == 0) {
                userSettingRepository.insertFlag( goalEditableFlag = UserSetting(goal_editable_flag = 0) )
            }
            goalEditableFlag = userSettingRepository.getGoalEditableFlag()!!
        }
    }

    override fun getGoalById(id: Int): Flow<Goal> = goalRepository.getGoalById(id)

    override fun getActivityGoal(): Flow<Goal> = goalRepository.getActivityGoal()

    override fun cancelActivityGoal() {
        ioScope.launch { goalRepository.cancelActivityGoal() }
    }

    override fun newActivityGoal(id: Int) {
        ioScope.launch {
            goalRepository.cancelActivityGoal()
            goalRepository.newActivityGoal(id)
        }
    }

    override fun insertGoal(goal: Goal) {
        ioScope.launch { goalRepository.insertGoal(goal) }
    }

    override fun deleteGoal(goal: Goal) {
        ioScope.launch { goalRepository.deleteGoal(goal) }
    }

    override fun updateGoalEditableFlag(flag: Int) {
        ioScope.launch { userSettingRepository.updateGoalEditableFlag(flag) }
    }

    override fun insertGoalEditableFlag(goalEditableFlag: UserSetting) {
        ioScope.launch { userSettingRepository.insertFlag(goalEditableFlag) }
    }

    override fun deleteGoalEditableFlag(goalEditableFlag: UserSetting) {
        ioScope.launch { userSettingRepository.deleteFlag(goalEditableFlag) }
    }
}



