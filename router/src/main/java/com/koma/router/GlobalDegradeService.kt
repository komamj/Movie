package com.koma.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import timber.log.Timber

@Route(path = "/global/degrade")
class GlobalDegradeService : DegradeService {
    override fun init(context: Context) {

    }

    override fun onLost(context: Context, postcard: Postcard?) {
        Timber.e("onLost postcard:${postcard.toString()}")
    }
}
