package com.shannan.nakollaol.features.binverification

import androidx.lifecycle.MutableLiveData
import com.shannan.nakollaol.core.platform.BaseViewModel
import com.shannan.nakollaol.domain.interactor.checkbin.CheckBinUseCase
import com.shannan.nakollaol.domain.interactor.checkbin.CheckBinUseCase.Params
import com.shannan.nakollaol.domain.interactor.savebin.BinCode
import javax.inject.Inject

class BinVerificationViewModel
@Inject constructor(private val checkBinUseCase: CheckBinUseCase) : BaseViewModel() {

    var binModel: MutableLiveData<BinModel> = MutableLiveData()

    fun checkBinCode(binCode: String) = checkBinUseCase(Params(binCode)) { it.either(::handleFailure, ::handleSuccess) }

    private fun handleSuccess(code: BinCode) {
        this.binModel.value = BinModel(code.binCode)
    }
}