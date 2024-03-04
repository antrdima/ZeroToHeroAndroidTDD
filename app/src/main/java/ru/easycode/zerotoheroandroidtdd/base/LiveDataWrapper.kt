package ru.easycode.zerotoheroandroidtdd.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.UiState

interface LiveDataWrapper : LiveDataProvider {

    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)

    class Base(private val liveData: MutableLiveData<UiState> = SingleLiveEvent()) :
        LiveDataWrapper {
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
