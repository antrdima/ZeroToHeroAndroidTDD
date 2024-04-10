package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.core.Now

interface Repository {

    interface Add {
        fun add(value: String) : Long
    }

    interface Read {
        fun list(): List<Item>
    }

    interface Delete {
        fun item(id: Long): Item
        fun delete(id: Long)
    }

    interface Change  : Delete{
        fun update(id: Long, newText: String)
    }

    interface Mutable : Read, Add
    interface All : Mutable, Change

    class Base(private val dataSource: ItemsDao, private val now: Now) : All {

        override fun list(): List<Item> {
            return dataSource.list().map { Item(it.id, it.text) }
        }

        override fun add(value: String) : Long{
            return dataSource.add(ItemCache(now.nowMillis(), value))
        }

        override fun update(id: Long, newText: String) {
            dataSource.update(ItemCache(id, newText))
        }

        override fun item(id: Long): Item {
            val itemCache = dataSource.item(id)
            return Item(itemCache.id, itemCache.text)
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }
    }
}
