package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.core.Screen
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteFragment

data class EditFolderScreen(val folderId: Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        val fragment = EditFolderFragment()
        fragment.arguments = bundleOf().apply {
            putLong("KEY", folderId)
        }
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}
