package com.shannan.nakollaol.data.net.restaurants

import com.shannan.nakollaol.data.NetworkHandler
import com.shannan.nakollaol.data.net.users.KeysService
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.Restaurant
import com.shannan.nakollaol.domain.repository.RestaurantsRepository
import javax.inject.Inject

class RestaurantsRepositoryImpl
@Inject constructor(private val networkHandler: NetworkHandler,
                    private val service: KeysService) : RestaurantsRepository {
    override fun getRestaurant(id: Int): Either<Failure, Restaurant> {

        val marzouka = Restaurant(0, "Marzouka", null, null, 7.00, emptyList())

        return when (networkHandler.isConnected) {
            true -> Either.Right(marzouka)
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun getRestaurants(): Either<Failure, List<Restaurant>> {

        val marzouka = Restaurant(0, "Marzouka", null, null, 7.00, emptyList())
        val felfela = Restaurant(0, "Felfela", null, null, 10.00, emptyList())

        val restaurantList: List<Restaurant> = arrayListOf(marzouka, felfela)

        return when (networkHandler.isConnected) {
            true -> Either.Right(restaurantList)
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }
}