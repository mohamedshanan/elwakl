package com.shannan.nakollaol.features.restaurantmenu

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.shannan.nakollaol.R
import com.shannan.nakollaol.Utils.Constants
import com.shannan.nakollaol.core.extension.failure
import com.shannan.nakollaol.core.extension.observe
import com.shannan.nakollaol.core.extension.viewModel
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.domain.exception.CodeVerificationFailure
import com.shannan.nakollaol.domain.exception.Failure
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import com.shannan.nakollaol.models.SandwichModel
import kotlinx.android.synthetic.main.fragment_restaurants.*
import javax.inject.Inject

class MenuFragment : BaseFragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    @Inject
    lateinit var menuAdapter: MenuAdapter
    private lateinit var menuViewModel: MenuViewModel

    override fun layoutId() = R.layout.fragment_restaurants

    override fun afterInflation(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        appComponent.inject(this)

        menuViewModel = viewModel(viewModelFactory) {
            observe(menuLiveData, ::renderMenuSandwiches)
            failure(failure, ::handleFailure)
        }
        loadRestaurant()
    }

    private fun initializeView() {
        restaurantsList.layoutManager = LinearLayoutManager(activity)
        restaurantsList.adapter = menuAdapter
        menuAdapter.clickListener = { restaurant ->
            //            navigator.showRestaurantScreen(activity!!, restaurant)
        }
    }

    private fun loadRestaurant() {
        setLoading(true)
        menuViewModel.getMenu()
        val sandwichModels: List<SandwichModel>? = arguments?.getParcelableArrayList(Constants.EXTRA_SANDWICHES)
    }

    private fun renderMenuSandwiches(restaurants: List<SandwichModel>?) {
        initializeView()
        Toast.makeText(activity, "" + restaurants?.size + " " + restaurants?.get(0)?.name, Toast.LENGTH_SHORT).show()
        menuAdapter.collection = restaurants.orEmpty()
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