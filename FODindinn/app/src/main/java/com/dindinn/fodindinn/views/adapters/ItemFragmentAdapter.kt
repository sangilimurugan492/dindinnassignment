package com.dindinn.fodindinn.views.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dindinn.fodindinn.views.ItemFragment

class ItemFragmentAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ItemFragment.newInstance()
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
