package com.shannan.nakollaol.features.restaurantmenu

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.inflate
import com.shannan.nakollaol.domain.interactor.Extra
import kotlinx.android.synthetic.main.row_sandwich.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ExtrasAdapter
@Inject constructor() : RecyclerView.Adapter<ExtrasAdapter.ViewHolder>() {

    internal var collection: List<Extra> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (Extra) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_sandwich))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(extra: Extra, clickListener: (Extra) -> Unit) {
            itemView.name.text = extra.name
            itemView.price.text = extra.price.toString()
            itemView.setOnClickListener { clickListener(extra) }
        }
    }
}