package com.shannan.nakollaol.domain.interactor.generateotp

import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.repository.OTPRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
class GenerateOTPUseCaseTest {

    private lateinit var generateOTPUseCase: GenerateOTPUseCase

    @Mock
    private lateinit var otpRepository: OTPRepository

    @Before
    fun setUp() {
        generateOTPUseCase = GenerateOTPUseCase(otpRepository)
        given { otpRepository.getOTP() }.willReturn(Either.Right(OTP.empty()))
    }

    @Test
    fun `should get otp from repository`() {
        runBlocking { generateOTPUseCase.run(GenerateOTPUseCase.Params(0)) }

        verify(otpRepository).getOTP()
        verifyNoMoreInteractions(otpRepository)
    }
}