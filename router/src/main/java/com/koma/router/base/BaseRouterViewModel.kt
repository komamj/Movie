package com.koma.router.base

import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter

class BaseRouterViewModel : ViewModel() {
    init {
        ARouter.getInstance().inject(this)
    }
}
