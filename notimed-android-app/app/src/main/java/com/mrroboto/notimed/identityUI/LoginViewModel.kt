package com.mrroboto.notimed.identityUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
}