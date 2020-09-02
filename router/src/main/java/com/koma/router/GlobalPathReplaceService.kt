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
