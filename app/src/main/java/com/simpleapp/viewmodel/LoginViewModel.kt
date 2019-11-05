package com.simpleapp.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(), LifecycleObserver {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var enableLogin = MediatorLiveData<Boolean>()

    init {
        enableLogin.apply {
            addSource(email) { this.value = validateLogin() }
            addSource(password) { this.value = validateLogin() }
        }
    }

    private fun validateLogin(): Boolean{
        return !email.value.isNullOrBlank() && !password.value.isNullOrBlank()
    }
}