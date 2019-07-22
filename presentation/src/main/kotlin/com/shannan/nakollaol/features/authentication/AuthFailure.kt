package com.shannan.nakollaol.features.authentication

import com.shannan.nakollaol.domain.exception.Failure.FeatureFailure

class AuthFailure {
    class InvalidEmail : FeatureFailure()
    class InvalidName : FeatureFailure()
    class InvalidPhone : FeatureFailure()
}

