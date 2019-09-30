package com.example.myapplication.ui

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footbalapplication.service.model.response.MatchResponse
import com.example.myapplication.view.MatchAdapter

import com.example.myapplication.R
import com.example.myapplication.databinding.MatchFragmentBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_fragment.*
import kotlinx.android.synthetic.main.match_progress_view.*
import kotlinx.android.synthetic.main.match_teams_tile_view.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlinx.android.synthetic.main.team_view.view.*
import java.util.*

class MatchFragment : Fragment() {
    private lateinit var toSpannableText: String
    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        return MatchFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this.lifecycleOwner
            viewModel = viewModel
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createMatch()
    }
    private fun createMatch() {
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        viewModel.getApiResponse().observe(this, androidx.lifecycle.Observer { match ->
            initUi(match)
            getProgress(match)
            initAdapter(match)
        })
    }

    private fun initUi(matchResponse: MatchResponse? = null){
        teamOneId.teamOne = matchResponse!!.match.team1!!.teamName
        teamTwoId.teamTwo = matchResponse.match.team2!!.teamName
        Picasso.get().load(matchResponse.match.team1!!.teamImage).into(teamOneId.ic_team)
        Picasso.get().load(matchResponse.match.team2.teamImage).into(teamTwoId.ic_team)
        toSpannableText = "${Date(matchResponse.match.matchDate!!)} ${matchResponse.match.stadiumAdress}"
        date_stadium_id.text = setSpannable(toSpannableText)
    }

    private fun initAdapter(matchResponse: MatchResponse? = null){
        recycler_view_id.layoutManager = LinearLayoutManager(context)
        recycler_view_id.adapter = MatchAdapter(matchResponse!!, this)
    }

    private fun getProgress(matchResponse: MatchResponse? = null){
        val teamOneBallPos: Int = matchResponse!!.match.team1!!.ballPosition!!
        val ourSize: Int = progress_main.width * teamOneBallPos / 100
        progress_green.layoutParams = RelativeLayout.LayoutParams(ourSize, progress_green.height)
    }

    private fun setSpannable(textContent: String): SpannableString {
        val spannableContent = SpannableString(textContent)
        spannableContent.setSpan(ForegroundColorSpan(Color.GREEN), 0, 34, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return spannableContent
    }


}
