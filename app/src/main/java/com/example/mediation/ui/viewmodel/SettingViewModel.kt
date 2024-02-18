package com.example.mediation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mediation.R
import com.example.mediation.ui.components.Music
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel() : ViewModel() {
    private val _musicIndex = MutableStateFlow(-1)
    val musicIndex = _musicIndex.asStateFlow()
    private val _hour = MutableStateFlow(0)
    val hour = _hour.asStateFlow()
    private val _minute = MutableStateFlow(0)
    val minute = _minute.asStateFlow()
    private val _second = MutableStateFlow(0)
    val second = _second.asStateFlow()

    fun onStart(){
        _musicIndex.value=-1
        _hour.value=0
        _minute.value=0
        _second.value=0
    }
    fun onHourChange(newValue: Int) {
        _hour.value = newValue
    }

    fun onMinuteChange(newValue: Int) {
        _minute.value = newValue
    }

    fun onSecondChange(newValue: Int) {
        _second.value = newValue
    }

    fun onMusicChange(newValue: Int) {
        _musicIndex.value = newValue
    }

    fun onConfirmPressed(
        navigateToHome: () -> Unit
    ) {
        navigateToHome()
    }
}

class SettingViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

