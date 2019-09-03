package com.shannan.nakollaol.features.restaurantmenu

import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.getrestaurant.GetRestaurant
import com.shannan.nakollaol.models.SandwichModel
import javax.inject.Inject

class MenuViewModel
@Inject constructor(private val getRestaurant: GetRestaurant) : BaseViewModel() {

    var menuLiveData: MutableLiveData<List<SandwichModel>> = MutableLiveData()

    fun getMenu() = getRestaurant(GetRestaurant.Params(0)) { it.either(::handleFailure, ::handleSuccess) }

    private fun handleSuccess(restaurant: Restaurant) {
        this.menuLiveData.value = restaurant.sandwiches.map { SandwichModel.fromDomain(it) }
    }
}