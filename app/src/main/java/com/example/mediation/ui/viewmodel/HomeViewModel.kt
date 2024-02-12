package com.example.mediation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val endTime: Long = 20L) : ViewModel() {
    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()
    private var timeJob: Job? = null

    //计时开始
    fun startTimer() {
        timeJob?.cancel()
        timeJob = viewModelScope.launch {
            while (_timer.value <= endTime) {
                delay(1000)
                _timer.value++
            }
        }
    }

    //计时暂停
    fun pauseTimer() {
        timeJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timeJob?.cancel()
    }
}