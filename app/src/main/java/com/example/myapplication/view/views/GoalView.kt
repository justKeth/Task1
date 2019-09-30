package com.example.myapplication.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.myapplication.R
import kotlinx.android.synthetic.main.goal_view.view.*

class GoalView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {
    var goalTime: String? = null
    set(value) {
        field = value
        if (value != null) {
            goalTimeBy.text = value
        }
    }

    var playerName: String? = null
    set(value) {
        field = value
        if (value != null){
            playerNameId.text = value
        }
    }

    init {
        View.inflate(context, R.layout.goal_view, this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.GoalView, 0, 0)
            goalTime = typedArray.getString(R.styleable.GoalView_name)
            playerName = typedArray.getString(R.styleable.GoalView_nameOfPlayer)
        }
    }
}