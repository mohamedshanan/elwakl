package com.shannan.nakollaol.features.restaurants

import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.interactor.getrestaurants.GetRestaurants
import javax.inject.Inject


class RestaurantsViewModel
@Inject constructor(private val getRestaurants: GetRestaurants) : BaseViewModel() {

    var restaurantLiveData: MutableLiveData<List<RestaurantModel>> = MutableLiveData()

    fun getRestaurants() = getRestaurants(UseCase.None()) { it.either(::handleFailure, ::handleSuccess) }

    private fun handleSuccess(restaurantList: List<Restaurant>) {
        this.restaurantLiveData.value = restaurantList.map { RestaurantModel.fromDomainRestaurant(it) }
    }
}