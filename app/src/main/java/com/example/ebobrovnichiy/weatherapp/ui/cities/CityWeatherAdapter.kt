package com.example.ebobrovnichiy.weatherapp.ui.cities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebobrovnichiy.weatherapp.R
import com.example.ebobrovnichiy.weatherapp.data.model.CityWeather
import kotlinx.android.synthetic.main.city_info_item.view.*

class CityWeatherAdapter(val longClicked: (CityWeather) -> Unit, val clickListener: (CityWeather) -> Unit) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    var items: List<CityWeather> = emptyList()

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(newSata: List<CityWeather>) {
        items = newSata
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherAdapter.ViewHolder {
        val viewHolder = CityWeatherAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_info_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CityWeatherAdapter.ViewHolder, position: Int) = holder.bind(items[position], clickListener, longClicked)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: CityWeather, listener: (CityWeather) -> Unit, longClicked: (CityWeather) -> Unit) = with(itemView) {
            cityInfo.text = item.name
            setOnClickListener {
                listener(item)
            }
            setOnLongClickListener {
                longClicked(item)
                true
            }
        }
    }
}