package com.example.footbalapplication.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.footbalapplication.service.model.response.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchRepository {

    private val mockyApi: MockyApi
    private var matchRepository: MatchRepository? = null

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mockyApi = retrofit.create(MockyApi::class.java)
    }


    fun getInstance(): MatchRepository{
        if (matchRepository == null) {
            matchRepository = MatchRepository()
        }
        return matchRepository as MatchRepository
    }

    fun getMatches(): MutableLiveData<MatchResponse>{
        val liveData = MutableLiveData<MatchResponse>()
        val call = mockyApi.getAllItems()

        call.enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                Log.e("Error ", "Sorry!")
            }
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                liveData.value = response.body()
            }
        })
        return liveData
    }
}