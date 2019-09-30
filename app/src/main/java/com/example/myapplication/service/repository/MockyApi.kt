package com.example.footbalapplication.service.repository

import com.example.footbalapplication.service.model.response.MatchResponse
import retrofit2.http.GET
import retrofit2.Call

interface MockyApi {

    @GET("5b9264193300006b00205fb9")
    fun getAllItems(): Call<MatchResponse>

}