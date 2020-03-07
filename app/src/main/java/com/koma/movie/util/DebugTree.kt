package com.koma.movie.util

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
