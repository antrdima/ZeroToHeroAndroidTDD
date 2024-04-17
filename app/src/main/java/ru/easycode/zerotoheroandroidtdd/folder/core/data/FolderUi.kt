package ru.easycode.zerotoheroandroidtdd.folder.core.data

import ru.easycode.zerotoheroandroidtdd.core.data.Data

data class FolderUi(val id: Long, val title: String, val notesCount: Int) : Data {
    override fun areItemsTheSame(other: Data): Boolean {
        if (other !is FolderUi) return false
        return id == other.id
    }
}
