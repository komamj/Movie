package com.koma.eventbus

data class Event<T>(val code: Int, val data: T)
