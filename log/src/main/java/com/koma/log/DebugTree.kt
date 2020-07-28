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

package com.koma.log

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val newTag = produceTag(tag)
        super.log(priority, newTag, message, t)
    }

    private fun produceTag(originTag: String?): String {
        val builder = StringBuilder()
        builder.append(LOG_TAG)
            .append(SEPARATOR)
            .append(originTag)
        return builder.toString()
    }

    companion object {
        private const val LOG_TAG = "KomaLog"
        private const val SEPARATOR = "----"
    }
}
