package ru.easycode.zerotoheroandroidtdd.folder.details

import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

class NoteListLiveDataWrapper {
    interface Create {

        fun create(noteUi: NoteUi)
    }

    interface Update {

        fun update(noteId: Long, newText: String)
    }

    interface UpdateListAndRead {

        fun update(notes: List<NoteUi>)
    }

}
