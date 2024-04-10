package ru.easycode.zerotoheroandroidtdd.ui.details

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.BottomSheetDetailsBinding

class DetailsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: DetailsViewModel
    lateinit var binding: BottomSheetDetailsBinding

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
        binding = BottomSheetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (this.activity?.application as App).viewModel(DetailsViewModel::class.java)
        activity?.onBackPressedDispatcher?.addCallback(onBackPressedCallback)

        val id = arguments?.getLong("KEY") ?: -1L
        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.itemTextView.text = it
            binding.itemInputEditText.setText(it)
        }
        binding.deleteButton.setOnClickListener {
            viewModel.delete(id)
            dismiss()
        }
        binding.updateButton.setOnClickListener {
            viewModel.update(id, binding.itemInputEditText.text.toString())
            dismiss()
        }
        viewModel.init(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }

    companion object {
        const val TAG = "DetailsBottomSheet"
    }
}

