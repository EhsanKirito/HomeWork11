package com.example.scollingfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var fullName = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var gender = MutableLiveData<String>()
}