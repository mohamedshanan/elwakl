package com.shannan.nakollaol.models

import android.os.Parcel
import com.shannan.nakollaol.core.platform.KParcelable
import com.shannan.nakollaol.core.platform.parcelableCreator
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.Sandwich

data class RestaurantModel(val name: String,
                           val photoUrl: String?,
                           val phone: String?,
                           val deliveryCost: String?,
                           val sandwiches: List<Sandwich> = emptyList()) : KParcelable {

    private constructor(p: Parcel) : this(name = p.readString(), photoUrl = p.readString(), phone = p.readString(), deliveryCost = p.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(name)
            writeString(photoUrl)
            writeString(phone)
            writeString(deliveryCost)
        }
    }

    companion object {
        fun fromDomainRestaurant(restaurant: Restaurant) =
                RestaurantModel(name = restaurant.name,
                        photoUrl = restaurant.photoUrl,
                        phone = restaurant.phone,
                        deliveryCost = restaurant.toString(),
                        sandwiches = restaurant.sandwiches
                )

        @JvmField
        val CREATOR = parcelableCreator(::RestaurantModel)
    }
}
