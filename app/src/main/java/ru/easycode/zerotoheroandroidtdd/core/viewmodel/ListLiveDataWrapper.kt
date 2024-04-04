package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<String>>
    interface Update : LiveDataWrapper.Update<List<String>>

    interface Mutable : Read, Update {
//        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add : ListLiveDataWrapper{
        fun add(value: String)
    }

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<String>>(), All {
//        override fun save(bundleWrapper: BundleWrapper.Save) {
//            bundleWrapper.save(ArrayList(liveData().value ?: listOf()))
//        }

        override fun add(value: String) {
            update((liveData().value ?: emptyList()) + value)
        }
    }
}