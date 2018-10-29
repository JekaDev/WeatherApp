package com.example.ebobrovnichiy.weatherapp.ui.city

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebobrovnichiy.weatherapp.R
import com.example.ebobrovnichiy.weatherapp.data.model.CityInfo
import kotlinx.android.synthetic.main.city_info_item.view.*

class CityInfoAdapter(val longClicked: (CityInfo) -> Unit, val clickListener: (CityInfo) -> Unit) : RecyclerView.Adapter<CityInfoAdapter.ViewHolder>() {

    var items: List<CityInfo> = emptyList()

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(newSata: List<CityInfo>) {
        items = newSata
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityInfoAdapter.ViewHolder {
        val viewHolder = CityInfoAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_info_item, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CityInfoAdapter.ViewHolder, position: Int) = holder.bind(items[position], clickListener, longClicked)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: CityInfo, listener: (CityInfo) -> Unit, longClicked: (CityInfo) -> Unit) = with(itemView) {
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