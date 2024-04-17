package ru.easycode.zerotoheroandroidtdd.folder.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

class FolderDetailsFragment : AbstractFragment<FragmentFolderDetailsBinding>() {

    private lateinit var viewModel: FolderDetailsViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    private val noteClickListener = object : NoteClickListener {
        override fun onClick(noteUi: NoteUi) {
            viewModel.editNote(noteUi)
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFolderDetailsBinding {
        return FragmentFolderDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel(FolderDetailsViewModel::class.java)

        val adapter = NotesAdapter(noteClickListener)
        binding.notesRecyclerView.adapter = adapter

        binding.addNoteButton.setOnClickListener {
            viewModel.createNote()
        }
        binding.editFolderButton.setOnClickListener {
            viewModel.editFolder()
        }
        viewModel.noteListLiveDataWrapper.liveData().observe(viewLifecycleOwner){
            adapter.update(it)
        }
        viewModel.folderLiveDataWrapper.liveData().observe(viewLifecycleOwner){
            binding.folderNameTextView.text = it.title
            binding.notesCountTextView.text = it.notesCount.toString()
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}

interface NoteClickListener {
    fun onClick(noteUi: NoteUi)
}