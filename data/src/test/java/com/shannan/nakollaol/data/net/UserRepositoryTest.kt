package com.shannan.nakollaol.data.net

import com.shannan.nakollaol.data.Entity.UserEntity
import com.shannan.nakollaol.data.NetworkHandler
import com.shannan.nakollaol.data.UnitTest
import com.shannan.nakollaol.domain.exception.Failure.NetworkConnection
import com.shannan.nakollaol.domain.exception.Failure.ServerError
import com.shannan.nakollaol.domain.functional.Either
import com.shannan.nakollaol.domain.functional.Either.Right
import com.shannan.nakollaol.domain.interactor.User
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.robolectric.annotation.Config
import retrofit2.Call
import retrofit2.Response

@Config(manifest = Config.NONE)
class UserRepositoryTest : UnitTest() {

    private lateinit var networkRepository: KeyRepositoryImpl

    @Mock
    private lateinit var networkHandler: NetworkHandler
    @Mock
    private lateinit var service: KeysService

    @Mock
    private lateinit var userCall: Call<UserEntity>
    @Mock
    private lateinit var userResponse: Response<UserEntity>

    @Before
    fun setUp() {
        networkRepository = KeyRepositoryImpl(networkHandler, service)
    }

    @Test
    fun `should return empty key by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { userResponse.body() }.willReturn(null)
        given { userResponse.isSuccessful }.willReturn(true)
        given { userCall.execute() }.willReturn(userResponse)
        given { service.verifyCode("123") }.willReturn(userCall)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldEqual Right(User.empty())
        verify(service).verifyCode("123")
    }

    @Test
    fun `code verification should get key from service`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { userResponse.body() }.willReturn(
                UserEntity("moh@e-masary.com","Mohamed", "122313423423", "0101203123"))
        given { userResponse.isSuccessful }.willReturn(true)
        given { userCall.execute() }.willReturn(userResponse)
        given { service.verifyCode("123") }.willReturn(userCall)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldEqual Right(User("moh@e-masary.com"))
        verify(service).verifyCode("123")
    }

    @Test
    fun `code verification service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `code verification service should return network failure when undefined connection`() {
        given { networkHandler.isConnected }.willReturn(null)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `code verification service should return server error if no successful response`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }

    @Test
    fun `code verification request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val movieDetails = networkRepository.verifyCode("123")

        movieDetails shouldBeInstanceOf Either::class.java
        movieDetails.isLeft shouldEqual true
        movieDetails.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}