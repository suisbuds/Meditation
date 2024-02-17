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

    private val _enableWriteMessage = MutableStateFlow(false)
    val enableWriteMessage = _enableWriteMessage.asStateFlow()

    private val _musicIndex = MutableStateFlow(0)
    val musicIndex = _musicIndex.asStateFlow()

    private var timeJob: Job? = null

    //计时开始
    fun startTimer() {
        _hasStarted.value = true
        _isRunning.value = true
        timeJob = viewModelScope.launch {
            while (_isRunning.value) {
                delay(1000)
                _currentTime.value++
                if (_currentTime.value > _endTime.value) {
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

    //处理设置页面传来的数据
    fun handleSettingData(data: List<Int>) {
        if (data.isNotEmpty()) {
            _currentTime.value = (((data[1] * 60 + data[2]) * 60 + data[3]) * 1000).toLong()
            _musicIndex.value = data[0]
        }
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