package com.example.fragment_hw3

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.fragment_hw3.databinding.DialogEditTextBinding

class CreateListDialog : DialogFragment() {
    private val viewModel: DataViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val editText = DialogEditTextBinding.inflate(LayoutInflater.from(requireContext())).root
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialogTitle))
            .setMessage(getString(R.string.dialogMessage))
            .setView(editText)
            .setPositiveButton(
                getString(R.string.createButton),
            ) { _, _ ->
                viewModel.loadListText(editText.text.toString())
                dismiss()
            }
            .setNegativeButton(getString(R.string.cancelButton)) { _, _ -> }
            .create()

        dialog.setOnShowListener { dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false }
        dialog.setCanceledOnTouchOutside(false)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (editText.text.length > 3) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = true
                }
            }
        })
        return dialog
    }

    companion object {
        const val TAG = "ListDialog"
    }
}