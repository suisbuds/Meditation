package com.example.meditation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditation.data.dao.MessageDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel():ViewModel(){
    private val _username=MutableStateFlow("")
    val username=_username.asStateFlow()
    private val _password=MutableStateFlow("")
    val password=_password.asStateFlow()

    fun onUsernameChanged(newValue:String){
        _username.value=newValue
    }

    fun onPasswordChanged(newValue: String){
        _password.value=newValue
    }

    fun onSignUpPressed(){

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
