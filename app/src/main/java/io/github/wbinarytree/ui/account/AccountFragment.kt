package io.github.wbinarytree.ui.account

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.rally.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import io.github.wbinarytree.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = PieData()
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(18.5f))
        entries.add(PieEntry(26.7f))
        entries.add(PieEntry(24.0f))
        entries.add(PieEntry(30.8f))
        data.dataSet = PieDataSet(entries, "test").apply {
            sliceSpace = 3f
            colors = listOf(Color.RED,Color.BLUE,Color.CYAN,Color.MAGENTA)
        }
        data.isHighlightEnabled = false
        data.setDrawValues(false)
        chart_overview.data = data
        chart_overview.setDrawCenterText(false)
        chart_overview.setTransparentCircleAlpha(0)
        chart_overview.setBackgroundColor(0x00000000)
        chart_overview.setDrawEntryLabels(false)
        chart_overview.setHoleColor(0x00000000)
        chart_overview.holeRadius = 96f
        chart_overview.description.isEnabled = false
        chart_overview.legend.isEnabled = false
        chart_overview.animateY(3000, Easing.EasingOption.EaseInCirc)
        chart_overview.invalidate()
    }
}