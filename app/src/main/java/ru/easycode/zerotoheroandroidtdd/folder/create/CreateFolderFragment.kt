package ru.easycode.zerotoheroandroidtdd.folder.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateFolderBinding
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding

class CreateFolderFragment : AbstractFragment<FragmentCreateFolderBinding>() {

    private lateinit var viewModel: CreateFolderViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }
    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateFolderBinding {
        return FragmentCreateFolderBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel(CreateFolderViewModel::class.java)
        binding.saveFolderButton.setOnClickListener {
            viewModel.createFolder(binding.createFolderEditText.text.toString())
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}