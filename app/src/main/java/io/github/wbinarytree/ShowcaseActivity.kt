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
    private val listener: (View) -> Unit = {
        val target = icons.indexOf(it.id)
        if (currentTab != target) {
            TransitionManager.beginDelayedTransition(inner_tab_layout, getAnimation())
            val constraintSet = getConstraintSet(target)
            constraintSet.applyTo(inner_tab_layout)
        }

    }
    private var currentTab: Int = 0

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
        overview_icon.setOnClickListener(listener)
        accounts_icon.setOnClickListener(listener)
        bills_icon.setOnClickListener(listener)
        budgets_icon.setOnClickListener(listener)
        settings_icon.setOnClickListener(listener)
    }

    private fun getConstraintSet(target: Int): ConstraintSet {
        val constraintSet = ConstraintSet().apply {
            clone(originLayout)
        }
        texts.forEachIndexed { index, id -> constraintSet.setVisibility(id, if (index != target) View.GONE else View.VISIBLE) }
        icons.forEachIndexed { index, id -> constraintSet.setAlpha(id, if (index != target) 0.6f else 1f) }
        currentTab = target
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