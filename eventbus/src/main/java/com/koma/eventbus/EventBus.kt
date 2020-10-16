package com.koma.eventbus

import com.koma.eventbus.data.entities.Event
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.meta.SubscriberInfoIndex

object EventBus {
    fun init(subscriberInfoIndexList: List<SubscriberInfoIndex>) {
        EventBus.builder().apply {
            subscriberInfoIndexList.forEach {
                addIndex(it)
            }
        }.installDefaultEventBus()
    }

    fun register(subscriber: Any) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber)
        }
    }

    fun unregister(subscriber: Any) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber)
        }
    }

    fun <T> postEvent(event: Event<T>) {
        EventBus.getDefault().post(event)
    }

    fun <T> postStickyEvent(event: Event<T>) {
        EventBus.getDefault().postSticky(event)
    }
}
