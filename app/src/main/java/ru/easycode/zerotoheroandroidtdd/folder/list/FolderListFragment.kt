package ru.easycode.zerotoheroandroidtdd.folder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderListBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

class FolderListFragment : AbstractFragment<FragmentFolderListBinding>() {

    private lateinit var viewModel: FolderListViewModel

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentFolderListBinding {
        return FragmentFolderListBinding.inflate(inflater, container, false)
    }

    private var clickListener = object : FolderClickListener {
        override fun onClick(folderUi: FolderUi) {
            viewModel.folderDetails(folderUi)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModel(FolderListViewModel::class.java)

        val adapter = FoldersAdapter(clickListener)
        binding.foldersRecyclerView.adapter = adapter

        viewModel.listLiveDataWrapper.liveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }
        binding.addButton.setOnClickListener {
            viewModel.addFolder()
        }
        viewModel.init()
    }
}

interface FolderClickListener {
    fun onClick(folderUi: FolderUi)
}