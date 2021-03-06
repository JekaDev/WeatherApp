package com.example.ebobrovnichiy.weatherapp.ui.cities

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import com.example.ebobrovnichiy.weatherapp.R

class WarningDialog : AppCompatDialogFragment() {

    companion object {
        const val EXTRA_MESSAGE = "Message"
        fun newInstance(message: String): WarningDialog {
            val dialog = WarningDialog()
            val args = Bundle().apply {
                putString(EXTRA_MESSAGE, message)
            }
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(EXTRA_MESSAGE)
        return AlertDialog.Builder(context!!)
                .setMessage(message)
                .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                    onResult?.invoke(true)
                }
                .setNegativeButton(getString(R.string.negative_button), null)
                .create()
    }

    var onResult: ((resat: Boolean) -> Unit)? = null
}