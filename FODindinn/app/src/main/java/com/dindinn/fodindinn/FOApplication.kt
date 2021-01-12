package com.dindinn.fodindinn

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.dindinn.fodindinn.data.FORepository

class FOApplication : Application() {
    val foRepository = FORepository()

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}