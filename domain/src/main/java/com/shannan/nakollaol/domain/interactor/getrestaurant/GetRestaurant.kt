package com.shannan.nakollaol.domain.interactor.getrestaurant

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.RestaurantsRepository
import javax.inject.Inject

class GetRestaurant
@Inject constructor(private val restaurantsRepository: RestaurantsRepository) : UseCase<Restaurant, GetRestaurant.Params>() {

    override suspend fun run(params: Params): Either<Failure, Restaurant> = restaurantsRepository.getRestaurant(params.restaurantID)

    data class Params(val restaurantID: Int)
}
