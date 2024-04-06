package ru.easycode.zerotoheroandroidtdd.core.livedata

import ru.easycode.zerotoheroandroidtdd.data.ItemUi

interface ListLiveDataWrapper {

    interface Read : LiveDataWrapper.Read<List<ItemUi>>
    interface Update : LiveDataWrapper.Update<List<ItemUi>>
    interface Mutable : Read, Update

    interface Add {
        fun add(value: ItemUi)
    }

    interface Delete {
        fun delete(item: ItemUi)
    }

    interface All : Mutable, Add, Delete

    class Base : LiveDataWrapper.Abstract<List<ItemUi>>(), All {
        override fun add(value: ItemUi) {
            update((liveData().value ?: emptyList()) + value)
        }

        override fun delete(item: ItemUi) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.remove(item)
            update(list)
        }
    }
}