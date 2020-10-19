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

    fun <T> cancelEventDelivery(event: Event<T>) {
        EventBus.getDefault().cancelEventDelivery(event)
    }

    fun <T> removeStickyEvent(event: Event<T>) {
        EventBus.getDefault().removeStickyEvent(event)
    }

    fun removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents()
    }
}
