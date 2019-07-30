package com.shannan.nakollaol.domain.repository

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.Restaurant

interface RestaurantsRepository {
    fun getRestaurants(): Either<Failure, List<Restaurant>>
    fun getRestaurant(id: Int): Either<Failure, Restaurant>
}
