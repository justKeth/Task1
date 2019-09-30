package com.example.myapplication.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.myapplication.R
import kotlinx.android.synthetic.main.team_view.view.*


class TeamView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    var teamOne: String? = null
        set(value) {
            field = value
            if (value != null)
                teamNameLabel?.text = value
        }

    var teamTwo: String? = null
        set(value) {
            field = value
            if (value != null)
                teamNameLabel?.text = value
        }


    init {
        View.inflate(context, R.layout.team_view, this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TeamView, 0, 0)
            teamOne = typedArray.getString(R.styleable.TeamView_nameOfTeamOne)
            teamTwo = typedArray.getString(R.styleable.TeamView_nameOfTeamTwo)
            typedArray.recycle()
        }
    }

//    @BindingAdapter("teamName")
//    fun TextView.setTeamName(text: String?){
//        this.tag = text?: ""
//        if (!text.isNullOrEmpty())
//            this.text = text
//    }
//    @InverseBindingAdapter(attribute = "teamName")
//    fun getTeamName(text: TextView?): String{
//        return  text?.tag as String
//    }
//    @BindingAdapter(value = ["teamNameAttrChanged"])
//    fun setListener(text: TextView?, listener: InverseBindingListener){
//        if (listener != null){
//            listener.onChange()
//        }
//    }
}