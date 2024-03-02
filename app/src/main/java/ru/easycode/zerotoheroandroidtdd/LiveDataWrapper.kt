package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper : ProvideLiveData {
    fun update(value: UiState)

    class Base : LiveDataWrapper {

        private val liveData = MutableLiveData<UiState>()

        override fun update(value: UiState) {
            liveData.postValue(value)
        }

        override fun liveData(): LiveData<UiState> {
            return liveData
        }
    }
}

interface ProvideLiveData {

    fun liveData(): LiveData<UiState>

}