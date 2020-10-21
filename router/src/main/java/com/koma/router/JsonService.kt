package com.koma.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import timber.log.Timber
import java.lang.reflect.Type

@Route(path = "/global/json")
class JsonService : SerializationService {
    private lateinit var gson: Gson

    override fun init(context: Context?) {
        Timber.d("init")

        gson = GsonBuilder().create()
    }

    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        return gson.fromJson(input, clazz)
    }

    override fun object2Json(instance: Any?): String {
        return gson.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        return gson.fromJson(input, clazz)
    }
}
