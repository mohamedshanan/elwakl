package com.shannan.nakollaol.features.restaurants

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.observe
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.CodeVerificationFailure
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import kotlinx.android.synthetic.main.fragment_menus.*
import javax.inject.Inject

class RestaurantsFragment : BaseFragment() {

    companion object {
        fun newInstance() = RestaurantsFragment()
    }

    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter
    private lateinit var restaurantsViewModel: RestaurantsViewModel

    override fun layoutId() = R.layout.fragment_menus

    override fun afterInflation(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        appComponent.inject(this)

        restaurantsViewModel = viewModel(viewModelFactory) {
            observe(restaurantLiveData, ::renderRestaurantList)
            failure(failure, ::handleFailure)
        }
        loadRestaurants()
    }

    private fun initializeView() {
        restaurantsList.layoutManager = LinearLayoutManager(activity)
        restaurantsList.adapter = restaurantsAdapter
        restaurantsAdapter.clickListener = { restaurant ->
            navigator.showRestaurantScreen(activity!!, restaurant)
        }
    }

    private fun loadRestaurants() {
        setLoading(true)
        restaurantsViewModel.getRestaurants()
    }

    private fun renderRestaurantList(restaurants: List<RestaurantModel>?) {
        initializeView()
        Toast.makeText(activity, "" + restaurants?.size + " " + restaurants?.get(0)?.name, Toast.LENGTH_SHORT).show()
        restaurantsAdapter.collection = restaurants.orEmpty()
        setLoading(false)
    }

    private fun handleFailure(failure: Failure?) {
        setLoading(false)
        when (failure) {
            is NetworkConnection -> {
                notify(R.string.failure_network_connection)
                activity?.finish()
            }
            is ServerError -> {
                notify(R.string.failure_server_error)
                activity?.finish()
            }
            is CodeVerificationFailure.InvalidVerificationCode -> {
                notify(R.string.failure_invalid_email)
                activity?.finish()
            }
        }
    }
}
