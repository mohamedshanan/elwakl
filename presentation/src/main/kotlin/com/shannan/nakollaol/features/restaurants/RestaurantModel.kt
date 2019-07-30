package com.shannan.nakollaol.features.restaurants

import com.shannan.nakollaol.domain.interactor.Restaurant

data class RestaurantModel(val name: String,
                           val photoUrl: String?,
                           val phone: String?,
                           val deliveryCost: String?) {
    companion object {
        fun fromDomainRestaurant(restaurant: Restaurant) =
                RestaurantModel(name = restaurant.name,
                        photoUrl = restaurant.photoUrl,
                        phone = restaurant.phone,
                        deliveryCost = restaurant.deliveryCost.toString()
                )
    }
}
