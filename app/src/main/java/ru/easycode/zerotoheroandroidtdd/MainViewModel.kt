package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) : ViewModel() {
    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    fun liveData(): LiveData<List<CharSequence>> {
        return listLiveDataWrapper.liveData()
    }
}