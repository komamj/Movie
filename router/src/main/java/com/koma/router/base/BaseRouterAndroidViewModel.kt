package com.koma.router.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alibaba.android.arouter.launcher.ARouter

class BaseRouterAndroidViewModel constructor(application: Application) :
    AndroidViewModel(application) {
    init {
        ARouter.getInstance().inject(this)
    }
}
