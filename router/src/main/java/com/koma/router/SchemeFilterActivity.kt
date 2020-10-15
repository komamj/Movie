package com.koma.router

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import timber.log.Timber

class SchemeFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")

        val uri = intent.data
        ARouter.getInstance().build(uri).navigation()
        finish()
    }
}
