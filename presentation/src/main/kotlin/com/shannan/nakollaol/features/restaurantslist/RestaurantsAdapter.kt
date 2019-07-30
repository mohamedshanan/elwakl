package com.shannan.nakollaol.features.restaurantslist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.inflate
import com.shannan.nakollaol.core.extension.loadFromUrl
import kotlinx.android.synthetic.main.row_restaurant.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class RestaurantsAdapter
@Inject constructor() : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    internal var collection: List<RestaurantModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (RestaurantModel) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_restaurant))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurantModel: RestaurantModel, clickListener: (RestaurantModel) -> Unit) {
            itemView.restaurantName.text = restaurantModel.name
            restaurantModel.photoUrl?.let { itemView.restaurantPhoto.loadFromUrl(it) }
            itemView.setOnClickListener { clickListener(restaurantModel) }
        }
    }
}