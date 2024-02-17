package com.example.mediation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mediation.R
import com.example.mediation.ui.components.Music
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel() : ViewModel() {
    private val _musicIndex = MutableStateFlow(0)
    val musicIndex = _musicIndex.asStateFlow()
    private val _hour = MutableStateFlow(0)
    val hour = _hour.asStateFlow()
    private val _minute = MutableStateFlow(0)
    val minute = _minute.asStateFlow()
    private val _second = MutableStateFlow(0)
    val second = _second.asStateFlow()
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
        musicIndex: Int,
        hour: Int,
        minute: Int,
        second: Int,
        navigateToHome: (List<Int>) -> Unit
    ) {
        navigateToHome(listOf(musicIndex, hour, minute, second))
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

val musicList: List<Music> = listOf(
    Music(R.drawable.shou_tan, "手谈", "专注", "竹林清脆，落子闻音"),
    Music(R.drawable.lin_feng, "林风", "冥想", "穿林而过的风"),
    Music(R.drawable.guang_yun, "光蕴", "情绪", "点点喜悦，束束希望"),
    Music(R.drawable.lin_feng, "林风", "专注", "竹林清脆，落子闻音"),
    Music(R.drawable.lin_feng, "手谈", "专注", "竹林清脆，落子闻音"),
    Music(R.drawable.guang_yun, "手谈", "专注", "竹林清脆，落子闻音"),
)