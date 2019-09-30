package com.example.footbalapplication.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.myapplication.R
import kotlinx.android.synthetic.main.substitution_view.view.*

class SubstitutionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {
    var substitutionTime: String? = null
    set(value) {
        field = value
        if(value != null)
            substitution_time_who.text = value
    }

    var playerOne: String? = null
    set(value) {
        field = value
        if(value != null)
            sub_player_one_name.text = value
    }

    var playerTwo: String? = null
    set(value) {
        field = value
        if(value != null)
            sub_player_two_name.text = value
    }


    init {
        View.inflate(context, R.layout.substitution_view, this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.SubstitutionView)
            substitutionTime = typedArray.getString(R.styleable.SubstitutionView_name)
            playerOne = typedArray.getString(R.styleable.SubstitutionView_nameOfPlayerOne)
            playerTwo = typedArray.getString(R.styleable.SubstitutionView_nameOfPlayerTwo)
        }
    }

}