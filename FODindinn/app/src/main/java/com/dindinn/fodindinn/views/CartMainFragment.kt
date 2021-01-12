package com.dindinn.fodindinn.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.dindinn.fodindinn.views.adapters.CartFragmentAdapter
import com.dindinn.fodindinn.MainFragmentHandler
import com.dindinn.fodindinn.MainFragmentViewModel
import com.dindinn.fodindinn.R
import com.dindinn.fodindinn.databinding.FragementCartLayoutBinding
import com.google.android.material.tabs.TabLayout

class CartMainFragment : Fragment(), MavericksView, MainFragmentHandler {

    private val viewModel: MainFragmentViewModel by activityViewModel()
    private lateinit var bindings: FragementCartLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragementCartLayoutBinding.inflate(inflater, container, false)
        bindings.handler = this
        initComponents()
        return bindings.root
    }

    private fun initComponents() {
        bindings.cartTabBar.addTab(bindings.cartTabBar.newTab().setText("Cart"))
        bindings.cartTabBar.addTab(bindings.cartTabBar.newTab().setText("Order"))
        bindings.cartTabBar.addTab(bindings.cartTabBar.newTab().setText("Information"))
        bindings.cartTabBar.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = CartFragmentAdapter(
                requireContext(),
                childFragmentManager,
                bindings.cartTabBar.tabCount
        )
        bindings.cartPager.adapter = adapter

        bindings.cartPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bindings.cartTabBar))

        bindings.cartTabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                bindings.cartPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun invalidate() = withState(viewModel) {state->
        bindings.state = state
    }

    override fun onCartClicked() {
            findNavController().popBackStack()
    }
}