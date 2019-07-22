package com.shannan.nakollaol.domain.interactor.verifyCode

import com.shannan.nakollaol.UnitTest
import com.shannan.nakollaol.domain.functional.Either.Right
import com.shannan.nakollaol.domain.interactor.User
import com.shannan.nakollaol.domain.interactor.verifycode.VerifyCodeUseCase
import com.shannan.nakollaol.domain.repository.KeyRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@Config(manifest = Config.NONE)
class VerifyCodeUseCaseTest : UnitTest() {

    private val FAKE_VERIFICATION_CODE = "123456"

    private lateinit var verifyCode: VerifyCodeUseCase

    @Mock
    private lateinit var keyRepository: KeyRepository

    @Before fun setUp() {
        verifyCode = VerifyCodeUseCase(keyRepository)
        given { keyRepository.verifyCode(FAKE_VERIFICATION_CODE) }.willReturn(Right(User.empty()))
    }

    @Test
    suspend fun `should get data from repository`() {
        runBlocking { verifyCode.run(VerifyCodeUseCase.Params(FAKE_VERIFICATION_CODE)) }

        var result = verify(keyRepository).verifyCode(FAKE_VERIFICATION_CODE)
        assertNotNull(result)
        verifyNoMoreInteractions(keyRepository)
    }
}
