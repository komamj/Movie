package com.koma.router

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

object Router {
    fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }

        ARouter.init(application)
    }
}
