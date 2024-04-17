package ru.easycode.zerotoheroandroidtdd.note.create

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.core.Screen

data class CreateNoteScreen(val folderId: Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        val fragment = CreateNoteFragment()
        fragment.arguments = bundleOf().apply {
            putLong("KEY", folderId)
        }
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}