package com.alejandro8a.androidTemplate.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>