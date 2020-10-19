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

import com.google.common.truth.Truth.assertThat
import com.koma.eventbus.data.entities.Event
import org.greenrobot.eventbus.EventBus as Bus
import org.greenrobot.eventbus.Subscribe
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EventBusTest {
    private lateinit var bus: Bus

    private val eventList = mutableListOf<Event<Any>>()

    @Before
    fun setUp() {
        Bus.clearCaches()

        bus = Bus.getDefault()
    }

    @After
    fun tearDown() {
        eventList.clear()
    }

    @Test
    fun `should return true when register called`() {
        assertThat(bus.isRegistered(this)).isFalse()

        EventBus.register(this)

        assertThat(bus.isRegistered(this)).isTrue()

        EventBus.unregister(this)
    }

    @Test
    fun `should return false when unregister called`() {
        EventBus.unregister(this)

        assertThat(bus.isRegistered(this)).isFalse()
    }

    @Test
    fun `should receive event when post event with subscribed firstly`() {
        EventBus.register(this)

        EventBus.postEvent(mockEvent(EVENT_CODE_NORMAL))

        EventBus.unregister(this)

        assertThat(eventList.size).isEqualTo(1)
    }

    @Test
    fun `should not receive event when post event without subscribed`() {
        EventBus.postEvent(mockEvent(EVENT_CODE_NORMAL))

        assertThat(eventList).isEmpty()
    }

    @Test
    @Throws(InterruptedException::class)
    fun `should receive event when post sticky event with subscribed`() {
        EventBus.postStickyEvent(mockEvent(EVENT_CODE_STICKY))
        EventBus.register(this)
        EventBus.postStickyEvent(mockEvent(EVENT_CODE_STICKY))
        EventBus.unregister(this)

        assertThat(eventList.size).isEqualTo(2)
    }

    @Test
    fun `should not receive event when post sticky event without subscribed`() {
        EventBus.postStickyEvent(mockEvent(EVENT_CODE_STICKY))

        assertThat(eventList).isEmpty()
    }

    private fun mockEvent(code: Int) = Event(code, null)

    @Subscribe
    fun onEvent(event: Event<Any>) {
        if (event.code == EVENT_CODE_NORMAL) {
            trackEvent(event)
        }
    }

    @Subscribe(sticky = true)
    fun onStickyEvent(event: Event<Any>) {
        if (event.code == EVENT_CODE_STICKY) {
            trackEvent(event)
        }
    }

    private fun trackEvent(event: Event<Any>) {
        eventList.add(event)
    }

    companion object {
        private const val EVENT_CODE_NORMAL = 0
        private const val EVENT_CODE_STICKY = 1
    }
}
