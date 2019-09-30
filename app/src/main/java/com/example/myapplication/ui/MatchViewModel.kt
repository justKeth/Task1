package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footbalapplication.service.model.response.MatchResponse
import com.example.footbalapplication.service.repository.MatchRepository

    @Suppress("DEPRECATION")
    class MatchViewModel: ViewModel() {

        private val mMatchRepository: MatchRepository = MatchRepository().getInstance()
        private val mMatchResponse: MutableLiveData<MatchResponse> = mMatchRepository.getMatches()

        var matchScore: MutableLiveData<String> = MutableLiveData()
        var matchTime: MutableLiveData<String> = MutableLiveData()
        var teamOneBallPosition: MutableLiveData<String> = MutableLiveData()
        var teamTwoBallPosition: MutableLiveData<String> = MutableLiveData()

        init {
            mMatchResponse.observeForever {
                matchScore.value = it.match.team1!!.score.toString() + ":" + it.match.team2!!.score.toString()
                matchTime.value = it.match.matchTime.toString()
                teamOneBallPosition.value = it.match.team1.ballPosition.toString()
                teamTwoBallPosition.value = it.match.team2.ballPosition.toString()
            }
        }

        fun getApiResponse(): LiveData<MatchResponse> {
            return mMatchResponse
        }
    }

