package ru.easycode.zerotoheroandroidtdd.note.edit

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.core.Screen

data class EditNoteScreen(val noteId: Long) : Screen {
    override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
        val fragment = EditNoteFragment()
        fragment.arguments = bundleOf().apply {
            putLong("KEY", noteId)
        }
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}
