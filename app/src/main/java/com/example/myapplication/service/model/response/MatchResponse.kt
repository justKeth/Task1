package com.example.footbalapplication.service.model.response

import com.example.footbalapplication.service.model.Match


data class MatchResponse(val resultCode: Int? = 0,
                         val match: Match)