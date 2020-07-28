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

import android.content.Context
import androidx.multidex.MultiDex
import com.koma.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import leakcanary.AppWatcher

@HiltAndroidApp
class MovieApplication : BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = true)
        }
    }
}
