package com.mrroboto.notimed.identityUI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecoverViewModel : ViewModel() {
    var currentEmail = MutableLiveData<String>()
}