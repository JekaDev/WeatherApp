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
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import com.example.ebobrovnichiy.weatherapp.R
import com.example.ebobrovnichiy.weatherapp.di.Injectable
import com.example.ebobrovnichiy.weatherapp.data.network.dto.Status.*
import com.example.ebobrovnichiy.weatherapp.data.model.CityInfo
import com.example.ebobrovnichiy.weatherapp.syncservice.ForecastUpdateJobService
import com.example.ebobrovnichiy.weatherapp.ui.fragment.WarningDialog
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.firebase.jobdispatcher.GooglePlayDriver
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.location.places.ui.PlaceAutocomplete.RESULT_ERROR
import javax.inject.Inject
import com.firebase.jobdispatcher.Constraint
import com.firebase.jobdispatcher.RetryStrategy
import com.firebase.jobdispatcher.Trigger
import com.firebase.jobdispatcher.Lifetime


class CitiesListFragment : Fragment(), Injectable {

    companion object {
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
        val TAG = CitiesListFragment::class.java.simpleName!!
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var cityViewModel: CityViewModel

    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var adapter: CityInfoAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cityViewModel = ViewModelProviders.of(this, viewModelFactory).get(CityViewModel::class.java)

        initRepoList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cities_list_fragment, container, false)

        setHasOptionsMenu(true)

        val newCityButton = view.findViewById(R.id.newCity) as FloatingActionButton;
        newCityButton.setOnClickListener {
            startAutocompleteActivity()
        }

        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            cityViewModel.update()
            swipeRefresh.isRefreshing = false
        }

        initAdapter(view)
        fireBaseJobDispatcher()
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
                cityViewModel.addNewCity(place.latLng.latitude, place.latLng.longitude)
                Log.i(TAG, "Place: " + place.latLng)
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

    private fun itemClicked(cityInfo: CityInfo) {
    }

    private fun longClicked(cityInfo: CityInfo) {
        val dialog = WarningDialog.newInstance(getString(R.string.warning_delete_city))
        dialog.onResult = { result ->
            cityViewModel.delete(cityInfo)
        }
        dialog.show(fragmentManager, "warningDialog")
    }

    private fun initAdapter(view: View) {
        val rvCitiesList = view.findViewById<RecyclerView>(R.id.rvCitiesList)
        adapter = CityInfoAdapter({ longClicked: CityInfo -> longClicked(longClicked) },
                { itemClicked: CityInfo -> itemClicked(itemClicked) })

        rvCitiesList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_update_period -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initRepoList() {
        cityViewModel.citiesInfo().observe(this, Observer { data ->

            when (data?.status) {
                LOADING -> {
                }
                SUCCESS -> {
                    adapter.addData(data.data!!)
                }
                ERROR -> {
                    view?.let { Snackbar.make(it, data.message.toString(), Snackbar.LENGTH_LONG).show() }
                }
            }
        })
    }

    private fun fireBaseJobDispatcher() {
        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(context))

        val myExtrasBundle = Bundle()
        myExtrasBundle.putString("some_key", "some_value")

        val job = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(ForecastUpdateJobService::class.java)
                // uniquely identifies the job
                .setTag("my-unique-tag")
                // one-off job
                .setRecurring(true)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(0, 10))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(true)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setExtras(myExtrasBundle)
                .build()

        dispatcher.mustSchedule(job)
    }
}