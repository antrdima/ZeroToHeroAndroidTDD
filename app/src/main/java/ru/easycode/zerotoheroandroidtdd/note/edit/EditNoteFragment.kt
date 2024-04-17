package ru.easycode.zerotoheroandroidtdd.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditNoteBinding

class EditNoteFragment : AbstractFragment<FragmentEditNoteBinding>() {

    private lateinit var viewModel: EditNoteViewModel

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentEditNoteBinding {
        return FragmentEditNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = arguments?.getLong("KEY") ?: -1
        viewModel = viewModel(EditNoteViewModel::class.java)

        binding.saveNoteButton.setOnClickListener {
            viewModel.renameNote(noteId, binding.noteEditText.text.toString())
        }
        binding.deleteNoteButton.setOnClickListener {
            viewModel.deleteNote(noteId)
        }
        viewModel.noteLiveDataWrapper.liveData().observe(viewLifecycleOwner){
            binding.noteEditText.setText(it)
        }
        viewModel.init(noteId)
    }
}