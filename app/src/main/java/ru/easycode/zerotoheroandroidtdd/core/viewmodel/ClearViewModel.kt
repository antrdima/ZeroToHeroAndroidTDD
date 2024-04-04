package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}