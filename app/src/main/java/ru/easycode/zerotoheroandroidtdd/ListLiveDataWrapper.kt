package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

interface ListLiveDataWrapper {
    fun liveData(): LiveData<List<CharSequence>>
    fun add(new: CharSequence)
    fun save(bundle: BundleWrapper.Save)
    fun update(list: List<CharSequence>)

    class Base(private val liveData: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent()) :
        ListLiveDataWrapper {
        override fun liveData(): LiveData<List<CharSequence>> {
            return liveData.map { it.toList() }
        }

        override fun add(new: CharSequence) {
            val list = liveData.value ?: arrayListOf()
            list.add(new)
            update(list)
        }

        override fun save(bundle: BundleWrapper.Save) {
            liveData.value?.let {
                bundle.save(it)
            }
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = ArrayList(list)
        }
    }
}
