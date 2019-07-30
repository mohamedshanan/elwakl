package com.shannan.nakollaol.features.restaurantslist

import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.Sandwich

data class RestaurantModel(val name: String,
                           val photoUrl: String?,
                           val phone: String?,
                           val deliveryCost: String?,
                           val sandwiches: List<Sandwich>) {
    companion object {
        fun fromDomainRestaurant(restaurant: Restaurant) =
                RestaurantModel(name = restaurant.name,
                        photoUrl = restaurant.photoUrl,
                        phone = restaurant.phone,
                        deliveryCost = restaurant.toString(),
                        sandwiches = restaurant.sandwiches
                )
    }
}
