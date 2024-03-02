package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper : ProvideLiveData {
    fun update(value: UiState)

    class Base : LiveDataWrapper {

        init{
            Log.d("TAG", "LiveDataWrapper base")
        }

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