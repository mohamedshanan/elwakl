package com.shannan.nakollaol.features.restaurantmenu

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.inflate
import com.shannan.nakollaol.features.restaurantslist.RestaurantsAdapter
import com.shannan.nakollaol.models.ExtraModel
import com.shannan.nakollaol.models.SandwichModel
import kotlinx.android.synthetic.main.row_sandwich.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MenuAdapter
@Inject constructor() : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    internal var collection: List<SandwichModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (SandwichModel) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_sandwich))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(sandwichModel: SandwichModel, clickListener: (SandwichModel) -> Unit) {
            itemView.name.text = sandwichModel.name
            itemView.price.text = sandwichModel.price.toString()
//            if (sandwichModel.extras?.isEmpty()){
//                itemView.extrasList.visibility = View.GONE
//            } else {
//                initializeExtrasList(itemView.context, itemView.extrasList, sandwichModel.extras)
//            }

            itemView.setOnClickListener { clickListener(sandwichModel) }
        }

        private fun initializeExtrasList(context: Context, extrasList: RecyclerView, extraModel: List<ExtraModel>?) {
            itemView.extrasList.visibility = View.VISIBLE
            lateinit var extrasAdapter: RestaurantsAdapter
            extrasList.layoutManager = LinearLayoutManager(context)
            extrasList.adapter = extrasAdapter

//            extrasAdapter.clickListener = { extraModel ->
//                navigator.showRestaurantScreen(extrasList?.context, extraModel)
//            }
        }
    }
}