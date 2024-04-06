package ru.easycode.zerotoheroandroidtdd.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.BottomSheetDeleteBinding


class DeleteBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: DeleteViewModel
    lateinit var binding: BottomSheetDeleteBinding

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (this.activity?.application as App).viewModel(DeleteViewModel::class.java)
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)

        val id = arguments?.getLong("KEY") ?: -1L
        viewModel.init(id)
        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTitleTextView.text = it
        }
        binding.deleteButton.setOnClickListener {
            viewModel.delete(id)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

    companion object {
        const val TAG = "DeleteBottomSheet"
    }
}

