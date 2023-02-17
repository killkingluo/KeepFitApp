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
    fun insert(goal: Goal)
    fun delete(goal: Goal)
}

@HiltViewModel
class GoalViewModel @Inject constructor(private val goalRepository: GoalRepository): ViewModel(), GoalViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override val getAllGoals: Flow<List<Goal>> = goalRepository.getAllGoals()

    override fun getGoalById(id: Int): Flow<Goal>  = goalRepository.getGoalById(id)

    override fun getActivityGoal(): Flow<Goal> = goalRepository.getActivityGoal()

    override fun cancelActivityGoal() {
        ioScope.launch { goalRepository.cancelActivityGoal() }
    }

    override fun newActivityGoal(id: Int) {
        ioScope.launch { goalRepository.newActivityGoal(id) }
    }

    override fun insert(goal: Goal) {
        ioScope.launch { goalRepository.insert(goal) }
    }

    override fun delete(goal: Goal) {
        ioScope.launch { goalRepository.delete(goal) }
    }
}


