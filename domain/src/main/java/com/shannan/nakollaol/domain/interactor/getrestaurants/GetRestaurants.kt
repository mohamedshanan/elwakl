package com.shannan.nakollaol.domain.interactor.getrestaurants

import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.interactor.UseCase
import com.shannan.nakollaol.domain.repository.RestaurantsRepository
import javax.inject.Inject

class GetRestaurants
@Inject constructor(private val restaurantsRepository: RestaurantsRepository) : UseCase<List<Restaurant>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Restaurant>> = restaurantsRepository.getRestaurants()
}
