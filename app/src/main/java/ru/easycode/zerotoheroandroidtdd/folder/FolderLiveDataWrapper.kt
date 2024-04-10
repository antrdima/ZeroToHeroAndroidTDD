package ru.easycode.zerotoheroandroidtdd.folder

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

    interface Rename {
        fun rename(newName: String)
    }

    interface Mutable : Update {
        fun folderId(): Long
    }
}
