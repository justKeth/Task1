package com.example.footbalapplication.service.model

data class Summaries(
        val actionTime: Int,
        val team1Action: MutableList<TeamAction>? = arrayListOf(),
        val team2Action: MutableList<TeamAction>? = arrayListOf()
)