package ru.easycode.zerotoheroandroidtdd.data

data class ItemUi(val id: Long, val text: String) {
    fun areItemsSame(item: ItemUi): Boolean {
        return id == item.id
    }
}
