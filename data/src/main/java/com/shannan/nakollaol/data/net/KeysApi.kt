package com.shannan.nakollaol.data.net

import com.shannan.nakollaol.data.Entity.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface KeysApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIE_DETAILS)
    fun verifyCode(@Path(PARAM_MOVIE_ID) movieId: String): Call<UserEntity>
}
