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
import com.dindinn.fodindinn.Constants
import com.dindinn.fodindinn.ItemClickListener
import com.dindinn.fodindinn.MainFragmentViewModel
import com.dindinn.fodindinn.data.FoodItem
import com.dindinn.fodindinn.data.network.APIClient
import com.dindinn.fodindinn.data.network.APIInterface
import com.dindinn.fodindinn.databinding.ViewPagerCartFragmentBinding
import com.dindinn.fodindinn.views.adapters.CartAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

@Suppress("UNCHECKED_CAST", "SetTextI18n")

class CartFragment(var isFrom : String?): Fragment(), MavericksView, ItemClickListener {

    private val viewModel : MainFragmentViewModel by activityViewModel()
    private val adapter = CartAdapter(isFrom!!)
    private lateinit var bindings: ViewPagerCartFragmentBinding
    private val compositeDisposable = CompositeDisposable()
    private var apiInterface : APIInterface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindings = ViewPagerCartFragmentBinding.inflate(inflater, container, false)
        val retrofit : Retrofit = APIClient.getInstance()
        apiInterface = retrofit.create(APIInterface::class.java)
        initComponents()
        return bindings.root
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun initComponents() {
        bindings.rvItem.layoutManager = LinearLayoutManager(requireActivity())
        bindings.rvItem.setHasFixedSize(true)


        bindings.rvItem.adapter = adapter
        if ("info" == isFrom) {
            getFetchData()
        } else {
            adapter.addListener(this)
            viewModel.getFoodItems().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.setMenu(it as MutableList<Any>)
                    calculateTotal(it)
                }
        }

    }

    private fun getFetchData() {
        compositeDisposable.add(apiInterface!!.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    adapter.setMenu(it as MutableList<Any>)
                })
        )
    }


    override fun invalidate() = withState(viewModel) {state->
        bindings.state = state
    }

    override fun clicked(int: Int) {
        adapter.removeItem(int)
        calculateTotal(adapter.getItems() as MutableList<FoodItem>)

    }

    private fun calculateTotal(it : MutableList<FoodItem>) {
        var total = 0
        for(item in it) {
            total += item.price
        }
        bindings.tvTotal.text = "Total $total"
    }
}