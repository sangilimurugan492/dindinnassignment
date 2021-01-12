package com.dindinn.fodindinn.views

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.dindinn.fodindinn.*
import com.dindinn.fodindinn.data.ImageModel
import com.dindinn.fodindinn.databinding.FragementMainLayoutBinding
import com.dindinn.fodindinn.views.adapters.ImageSliderAdapter
import com.dindinn.fodindinn.views.adapters.ItemFragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment(), MavericksView, MainFragmentHandler {

    private var runnable: Runnable? = null
    private val handler = Handler()
    private lateinit var adapterImageSlider : ImageSliderAdapter

    private val viewModel: MainFragmentViewModel by activityViewModel()
    private lateinit var bindings: FragementMainLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragementMainLayoutBinding.inflate(inflater, container, false)
        adapterImageSlider = ImageSliderAdapter(requireActivity(), Constants.getImageModel())
        bindings.pager.adapter = adapterImageSlider
        bindings.handler = this
        initComponnent()
        return bindings.root
    }

    private fun initComponnent() {
        bindings.pager.currentItem = 0
        addBottomDots(adapterImageSlider.count, 0)
        bindings.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                    pos: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(pos: Int) {
                addBottomDots(adapterImageSlider.count, pos)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        startAutoSlider(adapterImageSlider.count)

        bindings.foodTabBar.addTab(bindings.foodTabBar.newTab().setText("Desert"))
        bindings.foodTabBar.addTab(bindings.foodTabBar.newTab().setText("Combo"))
        bindings.foodTabBar.addTab(bindings.foodTabBar.newTab().setText("Snacks"))
        bindings.foodTabBar.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ItemFragmentAdapter(
                requireContext(),
                childFragmentManager,
                bindings.foodTabBar.tabCount
        )
        bindings.itemPager.adapter = adapter

        bindings.itemPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bindings.foodTabBar))

        bindings.foodTabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                bindings.itemPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    override fun invalidate() = withState(viewModel) { state ->
        bindings.state = state
    }

    private fun addBottomDots(size: Int, current: Int) {
        val dots = arrayOfNulls<ImageView>(size)
        bindings.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(requireContext())
            val height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(height, height))
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle_outline)
            bindings.layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[current]!!.setImageResource(R.drawable.shape_circle)
        }
    }

    private fun startAutoSlider(count: Int) {
        runnable = Runnable {
            var pos: Int = bindings.pager.getCurrentItem()
            pos += 1
            if (pos >= count) pos = 0
            bindings.pager.currentItem = pos
            handler.postDelayed(runnable!!, 3000)
        }
        handler.postDelayed(runnable!!, 3000)
    }


    override fun onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable!!)
        super.onDestroy()
    }

    override fun onCartClicked() {
        findNavController().navigate(R.id.action_main_to_cart)
    }
}
