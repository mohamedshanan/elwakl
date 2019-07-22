package com.shannan.nakollaol.domain.interactor.checkbin

import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.interactor.savebin.BinCode
import com.shannan.nakollaol.domain.repository.BinRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock

class CheckBinUseCaseTest {

    private val FAKE_BIN_CODE = "123456"

    private lateinit var checkBinUseCase: CheckBinUseCase

    @Mock
    private lateinit var binRepository: BinRepository

    @Before
    fun setUp() {

        checkBinUseCase = CheckBinUseCase(binRepository)
        given { binRepository.checkCode(FAKE_BIN_CODE) }.willReturn(Either.Right(BinCode.empty()))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { checkBinUseCase.run(CheckBinUseCase.Params(FAKE_BIN_CODE)) }

        verify(binRepository).checkCode(FAKE_BIN_CODE)
        verifyNoMoreInteractions(binRepository)
    }
}