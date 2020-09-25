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

import android.content.Context
import android.net.Uri
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.PathReplaceService
import timber.log.Timber

@Route(path = "/global/path_replace")
class GlobalPathReplaceService : PathReplaceService {
    override fun init(context: Context) {
        Timber.d("PathReplaceService init")
    }

    override fun forString(path: String): String {
        return path
    }

    override fun forUri(uri: Uri): Uri {
        return uri
    }
}
