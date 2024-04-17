package ru.easycode.zerotoheroandroidtdd.note.core.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.livedata.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

interface NoteListLiveDataWrapper {

    interface Create {
        fun create(noteUi: NoteUi)
    }

    interface Update {
        fun update(noteId: Long, newText: String)
    }

    interface UpdateList {
        fun update(notes: List<NoteUi>)
        fun liveData() : LiveData<List<NoteUi>>
    }
    interface All : Create, Update, UpdateList
    class Base(private val liveData: MutableLiveData<List<NoteUi>> = MutableLiveData()) : All {
        override fun create(noteUi: NoteUi) {
            val value = liveData.value ?: return
            liveData.value = value + noteUi
        }

        override fun update(noteId: Long, newText: String) {
            val value = liveData.value?.toMutableList() ?: return
            value.find { it.id == noteId }?.let {
                value[value.indexOf(it)] = it.copy(title = newText)
            }
            liveData.value = value
        }

        override fun update(notes: List<NoteUi>) {
            liveData.value = notes
        }

        override fun liveData(): LiveData<List<NoteUi>> {
            return liveData
        }
    }
}
