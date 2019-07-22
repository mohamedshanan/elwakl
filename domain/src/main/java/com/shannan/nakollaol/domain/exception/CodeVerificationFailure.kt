package com.shannan.nakollaol.domain.exception

import com.shannan.nakollaol.domain.exception.Failure.FeatureFailure

class CodeVerificationFailure {
    class InvalidBin : FeatureFailure()
    class InvalidVerificationCode : FeatureFailure()
}

