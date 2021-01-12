package com.dindinn.fodindinn.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.dindinn.fodindinn.MainFragmentViewModel
import com.dindinn.fodindinn.databinding.ViewPagerItemFragmentBinding
import com.dindinn.fodindinn.views.adapters.ItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ItemFragment: Fragment(), MavericksView {

    private lateinit var bindings: ViewPagerItemFragmentBinding
    private val adapter = ItemAdapter()
    private val viewModel: MainFragmentViewModel by activityViewModel()

    companion object {
        fun newInstance() = ItemFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindings = ViewPagerItemFragmentBinding.inflate(inflater, container, false)
        initComponents()
        return bindings.root
    }

    @SuppressLint("CheckResult")
    private fun initComponents() {
        bindings.rvItem.layoutManager = LinearLayoutManager(requireActivity())
        bindings.rvItem.setHasFixedSize(true)

        viewModel.getFoodItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.setMenu(it)
                }

//        adapter.addListener(this)
        bindings.rvItem.adapter = adapter
    }

    override fun invalidate() = withState(viewModel) {state->
        bindings.state = state

    }
}