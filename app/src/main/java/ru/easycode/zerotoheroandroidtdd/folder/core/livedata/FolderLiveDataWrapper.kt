package ru.easycode.zerotoheroandroidtdd.folder.core.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.livedata.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

interface FolderLiveDataWrapper {
    interface Increment {
        fun increment()
    }

    interface Decrement {
        fun decrement()
    }

    interface Update {
        fun update(folder: FolderUi)
    }

    interface Rename: Read {
        fun rename(newName: String)
    }

    interface Mutable : Update, Read {
        fun folderId(): Long
    }

    interface Read {
        fun liveData() : LiveData<FolderUi>
    }

    interface All : Increment, Decrement, Rename, Mutable

    class Base(private val liveData: MutableLiveData<FolderUi> = MutableLiveData()) : All {
        override fun increment() {
            val value = liveData.value ?: return
            liveData.value = value.copy(notesCount = value.notesCount + 1)
        }

        override fun decrement() {
            val value = liveData.value ?: return
            liveData.value = value.copy(notesCount = value.notesCount - 1)
        }

        override fun rename(newName: String) {
            val value = liveData.value ?: return
            liveData.value = value.copy(title = newName)
        }

        override fun liveData(): LiveData<FolderUi> {
            return liveData
        }

        override fun folderId(): Long {
            val value = liveData.value?: return 0
            return value.id
        }

        override fun update(folder: FolderUi) {
            liveData.value = folder
        }
    }
}
