package com.example.ebobrovnichiy.weatherapp.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebobrovnichiy.weatherapp.R

class DetailForecastFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detail_forecast_fragment, container, false)
        return view
    }
}