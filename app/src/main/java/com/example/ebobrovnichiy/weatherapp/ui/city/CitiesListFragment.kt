package com.example.ebobrovnichiy.weatherapp.ui.city

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebobrovnichiy.weatherapp.R
import com.example.ebobrovnichiy.weatherapp.di.Injectable
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.location.places.ui.PlaceAutocomplete.RESULT_ERROR
import javax.inject.Inject

class CitiesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var cityViewModel: CityViewModel

    companion object {
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
        val TAG = CitiesListFragment::class.java.simpleName
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cityViewModel = ViewModelProviders.of(this, viewModelFactory).get(CityViewModel::class.java)

        cityViewModel.forecastResponse.observe(this,
                Observer {

                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cities_list_fragment, container, false)

        val newCityButton = view.findViewById(R.id.newCity) as FloatingActionButton;
        newCityButton.setOnClickListener {
            //startAutocompleteActivity()
            cityViewModel.addData("Test")
        }

        return view
    }

    private fun startAutocompleteActivity() = try {
        val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(activity)
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
    } catch (e: GooglePlayServicesRepairableException) {
        Log.i(TAG, e.message)
    } catch (e: GooglePlayServicesNotAvailableException) {
        Log.i(TAG, e.message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != PLACE_AUTOCOMPLETE_REQUEST_CODE)
            return

        when (resultCode) {
            RESULT_OK -> {
                val place = PlaceAutocomplete.getPlace(activity, data)
                Log.i(TAG, "Place: " + place.name)
            }
            RESULT_ERROR -> {
                val status = PlaceAutocomplete.getStatus(activity, data)
                Log.i(TAG, status.statusMessage)
            }
            RESULT_CANCELED -> {
                Log.i(TAG, "User canceled")
            }
        }
    }
}