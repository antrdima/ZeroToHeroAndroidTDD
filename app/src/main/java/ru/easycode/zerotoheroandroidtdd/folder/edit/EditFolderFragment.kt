package ru.easycode.zerotoheroandroidtdd.folder.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditFolderBinding
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderListBinding
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel

class EditFolderFragment : AbstractFragment<FragmentEditFolderBinding>() {

    private lateinit var viewModel: EditFolderViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentEditFolderBinding {
        return FragmentEditFolderBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel(EditFolderViewModel::class.java)
        val folderId = arguments?.getLong("KEY") ?: -1
        binding.saveFolderButton.setOnClickListener {
            viewModel.renameFolder(folderId, binding.folderEditText.text.toString())
        }
        binding.deleteFolderButton.setOnClickListener {
            viewModel.deleteFolder(folderId)
        }
        viewModel.folderLiveDataWrapper.liveData().observe(viewLifecycleOwner){
            binding.folderEditText.setText(it.title)
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}