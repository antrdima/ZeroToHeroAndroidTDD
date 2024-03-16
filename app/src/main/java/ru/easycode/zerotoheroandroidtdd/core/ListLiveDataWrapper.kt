package ru.easycode.zerotoheroandroidtdd.core

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<CharSequence>>
    interface Update : LiveDataWrapper.Update<List<CharSequence>>
    interface Mutable : Read, Update {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Add {
        fun add(source: CharSequence)
    }

    interface All : Mutable, Add

    class Base : LiveDataWrapper.Abstract<List<CharSequence>>(), All {
        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(ArrayList(liveData().value ?: listOf()))
        }

        override fun add(source: CharSequence) {
            val list = liveData().value ?: listOf()
            val result = list + source
            update(result)
        }
    }
}