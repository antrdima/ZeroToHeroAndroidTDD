package ru.easycode.zerotoheroandroidtdd.folder.list

import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

interface FolderListLiveDataWrapper {
    interface UpdateListAndRead {

        fun update(list: List<FolderUi>)
    }

    interface Create {

        fun create(folderUi: FolderUi)
    }

}
