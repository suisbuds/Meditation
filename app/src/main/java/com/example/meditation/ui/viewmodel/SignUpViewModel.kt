package com.example.meditation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditation.ui.utils.checkUserExist
import com.example.meditation.ui.utils.insertUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    fun onSignUpPressed():Boolean{
        var result=false
        viewModelScope.launch {
            result= checkUserExist(_username.value)
            if(!result){
                insertUser(_username.value,_password.value)
            }
        }
        return !result
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
