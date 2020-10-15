package com.koma.apm

import com.koma.log.BuildConfig
import leakcanary.AppWatcher

object Apm {
    fun init() {
        if (BuildConfig.DEBUG) {
            AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = true)
        }
    }
}
