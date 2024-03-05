package ru.easycode.zerotoheroandroidtdd.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.ui.UiState

interface LiveDataWrapper : LiveDataProvider {

    interface Update : LiveDataWrapper {
        fun update(value: UiState)
    }

    interface Saver : LiveDataWrapper {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Mutable : Saver, Update

    class Base(private val liveData: MutableLiveData<UiState> = SingleLiveEvent()) :
        Mutable {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> {
            return liveData
        }
    }
}

interface LiveDataProvider {
    fun liveData(): LiveData<UiState>
}
