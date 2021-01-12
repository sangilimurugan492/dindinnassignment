package com.dindinn.fodindinn

import android.os.Build
import androidx.annotation.RequiresApi
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dindinn.fodindinn.data.FORepository
import com.dindinn.fodindinn.data.FoodItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

class MainFragmentViewModel  (
    initialState: FOState,
    private val foRepository: FORepository
) : MavericksViewModel<FOState>(initialState) {

    init {
        foRepository.getFoodItems()
    }

    fun getFoodItems() : Observable<List<FoodItem>> {
        return foRepository.getFoodItems()
    }

    companion object : MavericksViewModelFactory<MainFragmentViewModel, FOState> {
        override fun create(viewModelContext: ViewModelContext, state: FOState): MainFragmentViewModel? {
            val foRepository = viewModelContext.app<FOApplication>().foRepository
            return MainFragmentViewModel(state, foRepository)
        }
    }
}