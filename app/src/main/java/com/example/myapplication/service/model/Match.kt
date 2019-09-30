package com.example.footbalapplication.service.model

data class Match(
        val team1: Team? = null,
        val team2: Team? = null,
        val matchTime: Double? = null,
        val matchDate: Long? = null,
        val stadiumAdress: String? = null,
        val matchSummary: MatchSummary? = null
)