package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footbalapplication.service.enums.GoalType
import com.example.footbalapplication.service.enums.MatchActionType
import com.example.footbalapplication.service.model.response.MatchResponse
import com.example.myapplication.R
import com.example.myapplication.ui.MatchFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.goal_view.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.substitution_view.view.*

class MatchAdapter(private val item: MatchResponse, private var context: MatchFragment):
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return item.match.matchSummary!!.summaries!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val summary = item.match.matchSummary?.summaries?.get(position)

        holder.itemView.left_side.removeAllViews()
        holder.itemView.right_side.removeAllViews()

        for (team1Action in summary?.team1Action?: arrayListOf()){
            var view = View.inflate(context.context, R.layout.goal_view, null)

            when(team1Action.actionType){
                MatchActionType.GOAL.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                    when(team1Action.action.goalType){
                        GoalType.GOAL.value -> view.ballImage.setImageResource(R.drawable.match_goal_img)
                        GoalType.OWN_GOAL.value -> view.ballImage.setImageResource(R.drawable.match_own_goal_img)
                    }
                }
                MatchActionType.YELLOW_CARD.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                    view.ballImage.setImageResource(R.drawable.match_yellow_card_img)
                }
                MatchActionType.RED_CARD.value -> {
                    view.playerNameId.text = team1Action.action.player?.playerName
                    view.ballImage.setImageResource(R.drawable.match_yellow_card_img)
                    Picasso.get().load(team1Action.action.player?.playerImage).into(view.playerImage)
                }
                MatchActionType.SUBSTITUTION.value -> {
                    view = View.inflate(context.context, R.layout.substitution_view, null)
                    view.sub_player_one_name.text = team1Action.action.player1?.playerName
                    view.sub_player_two_name.text = team1Action.action.player2?.playerName
                    Picasso.get().load(team1Action.action.player1?.playerImage).into(view.sub_player_one_ic)
                    Picasso.get().load(team1Action.action.player2?.playerImage).into(view.sub_player_two_ic)
                }
            }
            holder.itemView.left_side.addView(view)
        }

        for (team2Action in summary?.team2Action?: arrayListOf()){
            var view = View.inflate(context.context, R.layout.goal_view, null)

            view.scaleX = -1f
            view.player_name.scaleX = -1f
            when(team2Action.actionType){
                MatchActionType.GOAL.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                    when(team2Action.action.goalType){
                        GoalType.GOAL.value -> view.ballImage.setImageResource(R.drawable.match_goal_img)
                        GoalType.OWN_GOAL.value -> view.ballImage.setImageResource(R.drawable.match_own_goal_img)
                    }
                }
                MatchActionType.YELLOW_CARD.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                    view.ballImage.setImageResource(R.drawable.match_yellow_card_img)
                }
                MatchActionType.RED_CARD.value -> {
                    view.playerNameId.text = team2Action.action.player?.playerName
                    view.ballImage.setImageResource(R.drawable.match_red_card_img)
                    Picasso.get().load(team2Action.action.player?.playerImage).into(view.playerImage)
                }
                MatchActionType.SUBSTITUTION.value -> {
                    view = View.inflate(context.context, R.layout.substitution_view, null)
                    view.scaleX = -1f
                    view.sub_player_names.scaleX = -1f
                    view.sub_player_one_name.text = team2Action.action.player1?.playerName
                    view.sub_player_two_name.text = team2Action.action.player2?.playerName
                    Picasso.get().load(team2Action.action.player1?.playerImage).into(view.sub_player_one_ic)
                    Picasso.get().load(team2Action.action.player2?.playerImage).into(view.sub_player_two_ic)
                }
            }
            holder.itemView.right_side.addView(view)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}