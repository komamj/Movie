package com.koma.eventbus

import org.greenrobot.eventbus.EventBus
import java.util.Objects

object EventBusUtils {
    private val eventBus = EventBus.getDefault()

    init {
        EventBus.builder().addIndex(EventBusIndex()).installDefaultEventBus()
    }

    fun register(subscriber: Objects) {
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber)
        }
    }

    fun unregister(subscriber: Objects) {
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber)
        }
    }

    fun <T> postEvent(event: Event<T>) {
        eventBus.post(event)
    }

    fun <T> postStickyEvent(event: Event<T>) {
        eventBus.postSticky(event)
    }
}
