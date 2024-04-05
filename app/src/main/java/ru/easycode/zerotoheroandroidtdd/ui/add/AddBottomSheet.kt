package ru.easycode.zerotoheroandroidtdd.ui.add

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.BottomSheetAddBinding

class AddBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_add) {

    private lateinit var binding: BottomSheetAddBinding
    private lateinit var viewModel: AddViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (this.activity?.application as App).viewModel(AddViewModel::class.java)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputText = dialog?.findViewById<TextInputEditText>(R.id.addInputEditText)
        val button  = dialog?.findViewById<Button>(R.id.saveButton)

        button?.setOnClickListener {
            viewModel.add(inputText?.text.toString())
            dismiss()
        }
    }

//    override fun onDismiss(dialog: DialogInterface) {
//        super.onDismiss(dialog)
//        viewModel.comeback()
//    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

