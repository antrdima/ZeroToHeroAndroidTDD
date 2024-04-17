package ru.easycode.zerotoheroandroidtdd.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : AbstractFragment<FragmentCreateNoteBinding>() {

    private lateinit var viewModel: CreateNoteViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateNoteBinding {
        return FragmentCreateNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val folderId = arguments?.getLong("KEY") ?: -1

        viewModel = viewModel(CreateNoteViewModel::class.java)
        binding.saveNoteButton.setOnClickListener {
            viewModel.createNote(folderId, binding.createNoteEditText.text.toString())
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}