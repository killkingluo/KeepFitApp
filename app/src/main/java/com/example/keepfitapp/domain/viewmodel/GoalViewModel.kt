package com.example.keepfitapp.domain.viewmodel


import androidx.lifecycle.*
import com.example.keepfitapp.domain.model.Goal
import com.example.keepfitapp.domain.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GoalViewModelAbstract {
    val getAllGoals: Flow<List<Goal>>
    fun getGoalById(id: Int): Flow<Goal>
    fun getActivityGoal(): Flow<Goal>
    fun cancelActivityGoal()
    fun newActivityGoal(id: Int)
    fun insertGoal(goal: Goal)
    fun deleteGoal(goal: Goal)
}

@HiltViewModel
class GoalViewModel @Inject constructor(private val goalRepository: GoalRepository): ViewModel(), GoalViewModelAbstract {

    override val getAllGoals: Flow<List<Goal>> = goalRepository.getAllGoals()
    private lateinit var currentSelectGoal: Goal

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun getCurrentSelectGoal(): Goal { return currentSelectGoal }

    fun setCurrentSelectGoal(goal: Goal?){
        if (goal != null) { currentSelectGoal = goal }
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
}



