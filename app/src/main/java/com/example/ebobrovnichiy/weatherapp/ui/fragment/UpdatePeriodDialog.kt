package com.example.ebobrovnichiy.weatherapp.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.ebobrovnichiy.weatherapp.R
import kotlinx.android.synthetic.main.update_period_dialog.view.*
import java.util.concurrent.TimeUnit

class UpdatePeriodDialog : AppCompatDialogFragment() {

    companion object {
        fun newInstance() = UpdatePeriodDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(R.layout.update_period_dialog, null as ViewGroup?)

        return AlertDialog.Builder(context!!)
                .setView(view)
                .setTitle(getString(R.string.title_period_update_dialog))
                .setPositiveButton(getString(R.string.positive_button)) { dialog, which ->
                    updatePeriod(view)?.let { onResult?.invoke(it) }
                }
                .setNegativeButton(getString(R.string.negative_button)) { dialog, which ->
                    dismiss()
                }
                .create()
    }

    private fun updatePeriod(view: View): Int? {
        val radio: RadioButton = view.findViewById(view.radio_group.checkedRadioButtonId)
        return when (radio.id) {
            R.id.period_30_min -> {
                TimeUnit.MINUTES.toSeconds(30).toInt()
            }
            R.id.period_1_h -> {
                TimeUnit.HOURS.toSeconds(1).toInt()
            }
            R.id.period_3_h -> {
                TimeUnit.HOURS.toSeconds(3).toInt()
            }
            else -> {
                null
            }
        }
    }

    var onResult: ((resat: Int) -> Unit)? = null
}