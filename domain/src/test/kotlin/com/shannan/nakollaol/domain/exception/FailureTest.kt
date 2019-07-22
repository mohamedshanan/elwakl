package com.shannan.nakollaol.domain.exception

import com.shannan.nakollaol.domain.exception.Failure.FeatureFailure
import org.junit.Before
import org.junit.Test

class FailureTest {


    private lateinit var serverError: Failure.ServerError
    private lateinit var networkConnection: Failure.NetworkConnection
    private lateinit var featureFailure: CodeVerificationFailure.InvalidVerificationCode

    @Before
    fun setUp() {
//        given { 1 }.willReturn(1)
        serverError = Failure.ServerError
        networkConnection = Failure.NetworkConnection
        featureFailure = CodeVerificationFailure.InvalidVerificationCode()
    }


    @Test
    fun `test failure types`() {
        assert(serverError is Failure.ServerError)
        assert(networkConnection is Failure.NetworkConnection)
        assert(featureFailure is FeatureFailure)
    }
}