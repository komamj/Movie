/*
 * Copyright 2020 komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.koma.movie

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.koma.commonlibrary.di.ApplicationModule
import com.koma.movie.di.AppComponent
import com.koma.movie.di.DaggerAppComponent
import com.koma.movie.di.RepositoryModule
import com.koma.movie.util.DebugTree
import com.koma.movie.util.ReleaseTree
import leakcanary.AppWatcher
import timber.log.Timber

class MovieApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .build()

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
                .build()
        )
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
