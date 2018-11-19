package io.github.wbinarytree.ui.main

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.transition.TransitionSet.ORDERING_TOGETHER
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.android.example.rally.R
import io.github.wbinarytree.base.BaseActivity
import io.github.wbinarytree.ui.account.AccountFragment
import io.github.wbinarytree.ui.empty.EmptyFragment
import io.github.wbinarytree.utils.OnPageSelectListener
import kotlinx.android.synthetic.main.activity_main_rally.*
import kotlinx.android.synthetic.main.layout_tabs_vertical.*

class MainActivity : BaseActivity() {

    private val originLayout by lazy {
        ConstraintSet().apply {
            this.clone(this@MainActivity, R.layout.layout_tabs_vertical)
        }
    }

    private val listener: (View) -> Unit = {
        val target = icons.indexOf(it.id)
        if (currentTab != target) {
            transitionToPage(target)
            main_pager.currentItem = target
        }
    }

    private var currentTab: Int = 0

    // TextView ids for for each tab
    private val texts = listOf(
        R.id.overview_text,
        R.id.account_text,
        R.id.bills_text,
        R.id.budgets_text,
        R.id.settings_text
    )

    // ImageView ids for for each tab
    private val icons = listOf(
        R.id.overview_icon,
        R.id.accounts_icon,
        R.id.bills_icon,
        R.id.budgets_icon,
        R.id.settings_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rally)
        overview_icon.setOnClickListener(listener)
        accounts_icon.setOnClickListener(listener)
        bills_icon.setOnClickListener(listener)
        budgets_icon.setOnClickListener(listener)
        settings_icon.setOnClickListener(listener)
        main_pager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    1    -> AccountFragment()
                    else -> EmptyFragment()
                }
            }

            override fun getCount(): Int = 5
        }
        main_pager.addOnPageChangeListener(OnPageSelectListener { position ->
            transitionToPage(position)
        })
    }


    private fun transitionToPage(target: Int) {
        val layout = tab_layout as ConstraintLayout
        TransitionManager.beginDelayedTransition(layout, getAnimation())
        val constraintSet = getConstraintSet(target)
        constraintSet.applyTo(layout)
    }

    private fun getConstraintSet(target: Int): ConstraintSet {
        val constraintSet = ConstraintSet().apply {
            clone(originLayout)
        }
        texts.forEachIndexed { index, id ->
            constraintSet.setVisibility(
                id,
                if (index != target) View.GONE else View.VISIBLE
            )
        }
        icons.forEachIndexed { index, id ->
            constraintSet.setAlpha(
                id,
                if (index != target) 0.6f else 1f
            )
        }
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