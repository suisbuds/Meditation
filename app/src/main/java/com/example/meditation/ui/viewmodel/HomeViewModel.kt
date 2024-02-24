package com.example.meditation.ui.viewmodel

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import com.example.meditation.MainActivity.Companion.appContext
import com.example.meditation.data.dao.MessageDao
import com.example.meditation.data.model.Message
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(private val messageDao: MessageDao) : ViewModel() {

    private val _endTime = MutableStateFlow(0L)
    val endTime = _endTime.asStateFlow()

    private val _currentTime = MutableStateFlow(0L)
    val currentTime = _currentTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private val _hasStarted = MutableStateFlow(false)
    val hasStarted = _hasStarted.asStateFlow()

    private val _enableWriteMessage = MutableStateFlow(false)
    val enableWriteMessage = _enableWriteMessage.asStateFlow()

    private val _musicUri = MutableStateFlow(Uri.EMPTY)
    val musicUri = _musicUri.asStateFlow()

    private var timeJob: Job? = null

    private var playMusic: Job? = null

    private var player: ExoPlayer? = null

    private var _isPlayingMusic = MutableStateFlow(false)
    val isPlayingMusic = _isPlayingMusic.asStateFlow()

    private val _historyMessages = MutableStateFlow(emptyList<Message>())
    val historyMessages = _historyMessages.asStateFlow()

    private var messageContent by mutableStateOf("")

    private var messageTitle by mutableStateOf("")

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
    fun handleSettingData(data: List<Int>, musicUri: Uri, clearSettingViewModelData: () -> Unit) {
        if (data.isNotEmpty()) {
            _endTime.value = ((data[0] * 60 + data[1]) * 60 + data[2]).toLong()
            Log.d("HomeViewModel", "time: ${_currentTime.value}+${_endTime.value}")
            _musicUri.value = musicUri
            Log.d("HomeViewModel", "music_uri: ${_musicUri.value}")
            clearSettingViewModelData()
            player = ExoPlayer.Builder(appContext).build()
            player!!.setMediaItem(
                MediaItem.fromUri(
                    _musicUri.value
                )
            )
            player!!.repeatMode = REPEAT_MODE_ALL
            playMusic = viewModelScope.launch {
                player!!.prepare()
                player!!.play()
                _isPlayingMusic.value = true
            }
        }
    }

    //控制音乐播放按钮点击事件
    fun onMusicControllerClicked() {
        if (player != null) {
            if (_isPlayingMusic.value) {
                _isPlayingMusic.value = false
                player!!.pause()
            } else {
                _isPlayingMusic.value = true
                player!!.play()
            }
        }

    }

    //计时结束,重置
    private fun endTimer() {
        timeJob?.cancel()
        _enableWriteMessage.value = true
        _currentTime.value = 0
        _endTime.value = 0
        _isRunning.value = false
        _hasStarted.value = false
        playMusic?.cancel()
        player?.release()
    }

    override fun onCleared() {
        super.onCleared()
        timeJob?.cancel()
    }

    //close message
    @RequiresApi(Build.VERSION_CODES.O)
    fun closeMessageCard() {
        val message = Message(
            title = messageTitle,
            content = messageContent,
            time = LocalDate.now().toString(),
            id = System.currentTimeMillis()
        )
        insertMessage(message)
        _enableWriteMessage.value = false
        messageTitle = ""
        messageContent = ""
    }

    fun getAllMessages() {
        viewModelScope.launch {
            messageDao.getAllMessages().collect { response -> _historyMessages.value = response }
        }
    }

    private fun insertMessage(message: Message) {
        viewModelScope.launch {
            messageDao.insertMessage(message)
        }
    }

    fun swipeToDeleteMessage(message: Message) {
        viewModelScope.launch { messageDao.deleteMessage(message) }
    }

    fun updateTitle(input: String) {
        messageTitle = input
    }

    fun updateContent(input: String) {
        messageContent = input
    }
}

class HomeViewModelFactory(private val messageDao: MessageDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(messageDao = messageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}