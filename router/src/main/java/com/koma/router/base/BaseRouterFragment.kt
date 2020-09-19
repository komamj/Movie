package com.koma.router.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

class BaseRouterFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)

        ARouter.getInstance().inject(this)
    }
}
