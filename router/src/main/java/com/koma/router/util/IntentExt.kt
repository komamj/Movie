package com.koma.router.util

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter

fun Intent.getRawUri(): String? = getStringExtra(ARouter.RAW_URI)
