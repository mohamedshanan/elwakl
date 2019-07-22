package com.shannan.nakollaol.data.net

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeysService
@Inject constructor(retrofit: Retrofit) : KeysApi {
    private val moviesApi by lazy { retrofit.create(KeysApi::class.java) }

    override fun verifyCode(movieId: String) = moviesApi.verifyCode(movieId)
}
