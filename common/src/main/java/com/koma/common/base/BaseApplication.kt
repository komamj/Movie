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

package com.koma.common.base

import android.app.Application
import android.os.StrictMode
import com.alibaba.android.arouter.launcher.ARouter
import com.koma.common.BuildConfig
import com.koma.log.DebugTree
import com.koma.log.ReleaseTree
import timber.log.Timber

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())

            ARouter.openLog()
            ARouter.openDebug()

            enabledStrictMode()
        } else {
            Timber.plant(ReleaseTree())
        }
        ARouter.init(this)
    }

    private fun enabledStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}
