package com.example.mediation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _endTime = MutableStateFlow(10L)
    val endTime = _endTime.asStateFlow()

    private val _currentTime = MutableStateFlow(0L)
    val currentTime = _currentTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _hasStarted = MutableStateFlow(false)
    val hasStarted = _hasStarted.asStateFlow()

    private var timeJob: Job? = null

    //计时开始
    fun startTimer() {
        _hasStarted.value = true
        _isRunning.value = true
        timeJob = viewModelScope.launch {
            while (_hasStarted.value) {
                delay(1000)
                _currentTime.value++
                if (_currentTime.value == _endTime.value) {
                    delay(1000)
                    endTimer()
                }
            }
        }
    }

    //计时暂停
    fun pauseTimer() {
        timeJob?.cancel()
        _isRunning.value = false
    }

    //计时结束,重置
    private fun endTimer() {
        timeJob?.cancel()
        _currentTime.value = 0
        _isRunning.value = false
        _hasStarted.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timeJob?.cancel()
    }
}