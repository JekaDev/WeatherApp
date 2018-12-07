package com.example.ebobrovnichiy.weatherapp.ui.cities

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.ebobrovnichiy.weatherapp.R
import com.example.ebobrovnichiy.weatherapp.data.prefs.AppPreferencesHelper
import com.example.ebobrovnichiy.weatherapp.di.Injectable
import kotlinx.android.synthetic.main.update_period_dialog.view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdatePeriodDialog : AppCompatDialogFragment(), Injectable {

    @Inject
    lateinit var appPreferencesHelper: AppPreferencesHelper

    companion object {
        fun newInstance() = UpdatePeriodDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(R.layout.update_period_dialog, null as ViewGroup?)
        val radio: RadioButton = view.findViewById(view.radio_group.checkedRadioButtonId)


        return AlertDialog.Builder(context!!)
                .setView(view)
                .setTitle(getString(R.string.title_period_update_dialog))
                .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                    updatePeriod(radio.id)?.let {
                        onResult?.invoke(it)
                    }
                }
                .setNegativeButton(getString(R.string.negative_button)) { _, _ ->
                    dismiss()
                }
                .create()
    }

    private fun updatePeriod(radionButtonId: Int): Int? {
        return when (radionButtonId) {
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