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

package com.koma.router

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.annotation.AnimRes
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

class Navigator {
    private val postcard: Postcard

    constructor(path: String) {
        postcard = ARouter.getInstance().build(path)
    }

    constructor (uri: Uri) {
        postcard = ARouter.getInstance().build(uri)
    }

    fun withString(key: String?, value: String?) = apply {
        this.postcard.withString(key, value)
    }

    fun withTransition(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int) = apply {
        this.postcard.withTransition(enterAnim, exitAnim)
    }

    fun withObject(key: String?, any: Any?) = apply {
        this.postcard.withObject(key, any)
    }

    fun greenChannel() = apply {
        this.postcard.greenChannel()
    }

    fun navigation(): Any {
        return this.postcard.navigation()
    }

    fun navigation(context: Context): Any {
        return this.postcard.navigation(context)
    }

    fun navigation(context: Context, callback: NavigatorCallback) {
        this.postcard.navigation(context, callback)
    }

    fun navigation(activity: Activity, requestCode: Int) {
        this.postcard.navigation(activity, requestCode)
    }

    fun navigation(activity: Activity, requestCode: Int, callback: NavigatorCallback) {
        this.postcard.navigation(activity, requestCode, callback)
    }
}
