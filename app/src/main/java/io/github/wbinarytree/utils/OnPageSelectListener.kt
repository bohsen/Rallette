package io.github.wbinarytree.utils

import androidx.viewpager.widget.ViewPager

class OnPageSelectListener(inline val onSelect: (Int) -> Unit) : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
        /* empty implementation*/
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        /* empty implementation*/
    }

    override fun onPageSelected(position: Int) {
        onSelect(position)
    }

}