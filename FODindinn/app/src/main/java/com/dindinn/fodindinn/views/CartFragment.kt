package com.dindinn.fodindinn.views

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
import com.dindinn.fodindinn.MainFragmentViewModel
import com.dindinn.fodindinn.data.network.APIClient
import com.dindinn.fodindinn.data.network.APIInterface
import com.dindinn.fodindinn.databinding.ViewPagerCartFragmentBinding
import com.dindinn.fodindinn.views.adapters.CartAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class CartFragment: Fragment(), MavericksView {

    private val viewModel : MainFragmentViewModel by activityViewModel()
    private val adapter = CartAdapter()
    private lateinit var bindings: ViewPagerCartFragmentBinding
    private val compositeDisposable = CompositeDisposable()
    private var apiInterface : APIInterface? = null

    companion object {
        private var isFrom : String? = null
        fun newInstance() = CartFragment()
        fun setType(from : String) {
            isFrom = from
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        bindings = ViewPagerCartFragmentBinding.inflate(inflater, container, false)
        val retrofit : Retrofit = APIClient.getInstance()
        apiInterface = retrofit.create(APIInterface::class.java)
        initComponents()
        return bindings.root
    }

    private fun initComponents() {
        bindings.rvItem.layoutManager = LinearLayoutManager(requireActivity())
        bindings.rvItem.setHasFixedSize(true)

//        adapter.addListener(this)
        bindings.rvItem.adapter = adapter
        if (isFrom.equals("info")) {
            getFetchData()
        } else {
            adapter.setMenu(Constants.getImageModel())
        }

    }

    private fun getFetchData() {
        compositeDisposable.add(apiInterface!!.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    adapter.setMenu(it)
                })
        )
    }


    override fun invalidate() = withState(viewModel) {state->
        bindings.state = state
    }
}