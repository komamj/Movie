package com.koma.eventbus.data.entities

data class Event<T>(val code: Int, val data: T)
