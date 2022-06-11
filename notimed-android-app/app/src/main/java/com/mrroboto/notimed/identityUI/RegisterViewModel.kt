package com.mrroboto.notimed.identityUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var currentName = MutableLiveData<String>()
    var currentLastname = MutableLiveData<String>()
    var currentEmail = MutableLiveData<String>()
    var currentPassword = MutableLiveData<String>()
    var currentBirthday = MutableLiveData<String>()
    var currentGender = MutableLiveData<String>()
}