package com.koma.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import timber.log.Timber

@Interceptor(priority = 8, name = "登录拦截器")
class AuthInterceptor : IInterceptor {
    override fun init(context: Context?) {
        Timber.d("init")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Timber.d("postcard:$postcard")
    }
}
