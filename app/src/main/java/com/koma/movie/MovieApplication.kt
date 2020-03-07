package com.koma.movie

import android.app.Application
import android.os.StrictMode
import com.koma.movie.util.DebugTree
import com.koma.movie.util.ReleaseTree
import leakcanary.AppWatcher
import timber.log.Timber

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())

            enabledStrictMode()

            AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = true)
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private fun enabledStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }
}