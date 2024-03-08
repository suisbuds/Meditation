package com.example.meditation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditation.ui.utils.checkUserExist
import com.example.meditation.ui.utils.searchUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    fun onUsernameChanged(newValue: String) {
        _username.value = newValue
    }

    fun onPasswordChanged(newValue: String) {
        _password.value = newValue
    }

    fun onLoginCheck(): Boolean {
        var userExist = false
        viewModelScope.launch {
            userExist = checkUserExist(_username.value)
        }
        return userExist
    }

    fun onLoginPressed(): Boolean {
        var loginResult = false
        viewModelScope.launch {
            loginResult = searchUser(_username.value, _password.value)
        }
        return loginResult
    }


}

class LoginViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}