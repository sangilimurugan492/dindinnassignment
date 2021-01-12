package com.dindinn.fodindinn.views.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dindinn.fodindinn.views.CartFragment

class CartFragmentAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        if (position == 1) {
            CartFragment.setType("info")
        }
        return CartFragment.newInstance()
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}
