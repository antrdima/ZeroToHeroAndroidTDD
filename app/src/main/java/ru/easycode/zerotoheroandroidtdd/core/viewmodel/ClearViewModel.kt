package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(clazz: Class<out ViewModel>)
}