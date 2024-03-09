package com.example.meditation.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditation.ui.utils.checkUserExist
import com.example.meditation.ui.utils.insertUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel() : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _result = MutableStateFlow(false)
    val result = _result.asStateFlow()

    fun onUsernameChanged(newValue: String) {
        _username.value = newValue
    }

    fun onPasswordChanged(newValue: String) {
        _password.value = newValue
    }

    suspend fun onSignUpPressed() {
        _result.value = checkUserExist(_username.value)
        if (!_result.value) {
            insertUser(_username.value, _password.value)
            _result.value = checkUserExist(_username.value)
            Log.d("DEBUG", "result value in sign viewModel ${_result.value}")
        }
    }

    fun cleanSignUpState() {
        viewModelScope.launch {
            _result.value = false
            _password.value = ""
            _username.value = ""
        }
    }
}

class SignUpViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
