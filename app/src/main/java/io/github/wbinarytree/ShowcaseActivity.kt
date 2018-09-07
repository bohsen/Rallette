package io.github.wbinarytree

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.transition.TransitionSet.ORDERING_TOGETHER
import android.view.View
import com.android.example.rally.R
import io.github.wbinarytree.base.BaseActivity
import kotlinx.android.synthetic.main.layout_tabs_vertical.*

class ShowcaseActivity : BaseActivity() {

    private val originLayout by lazy {
        ConstraintSet().apply {
            this.clone(this@ShowcaseActivity, R.layout.layout_tabs_vertical)
        }
    }

    private val texts = listOf(
        R.id.overview_text,
        R.id.account_text,
        R.id.bills_text,
        R.id.budgets_text,
        R.id.settings_text
    )
    private val icons = listOf(
        R.id.overview_icon,
        R.id.accounts_icon,
        R.id.bills_icon,
        R.id.budgets_icon,
        R.id.settings_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tabs_vertical)


        overview_icon.setOnClickListener {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(0)
            constraintSet.applyTo(inner_tab_layout)
        }

        accounts_icon.setOnClickListener {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(1)
            constraintSet.applyTo(inner_tab_layout)
        }

        bills_icon.setOnClickListener {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(2)
            constraintSet.applyTo(inner_tab_layout)
        }
        budgets_icon.setOnClickListener {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(3)
            constraintSet.applyTo(inner_tab_layout)
        }

        settings_icon.setOnClickListener {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(4)
            constraintSet.applyTo(inner_tab_layout)
        }
    }

    private fun getConstraintSet(position: Int): ConstraintSet {
        val constraintSet = ConstraintSet().apply {
            clone(originLayout)
        }
        texts.forEach { constraintSet.setVisibility(it, View.GONE) }
        icons.forEach { constraintSet.setAlpha(it, 0.6f) }
        when (position) {
            0 -> {
                constraintSet.setVisibility(R.id.overview_text, View.VISIBLE)
                constraintSet.setAlpha(R.id.overview_icon, 1f)
            }
            1 -> {
                constraintSet.setVisibility(R.id.account_text, View.VISIBLE)
                constraintSet.setAlpha(R.id.accounts_icon, 1f)
            }
            2 -> {
                constraintSet.setVisibility(R.id.bills_text, View.VISIBLE)
                constraintSet.setAlpha(R.id.bills_icon, 1f)
            }
            3 -> {
                constraintSet.setVisibility(R.id.budgets_text, View.VISIBLE)
                constraintSet.setAlpha(R.id.budgets_icon, 1f)
            }
            4 -> {
                constraintSet.setVisibility(R.id.settings_text, View.VISIBLE)
                constraintSet.setAlpha(R.id.settings_icon, 1f)
            }

        }
        return constraintSet
    }

    private fun getAnimation(): TransitionSet {
        val transitionSet = AutoTransition()
        transitionSet.ordering = ORDERING_TOGETHER
        transitionSet.duration = 300
        transitionSet.interpolator = FastOutSlowInInterpolator()
        return transitionSet
    }
}