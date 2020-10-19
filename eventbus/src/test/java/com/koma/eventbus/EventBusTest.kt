package com.koma.eventbus

import com.google.common.truth.Truth.assertThat
import com.koma.eventbus.data.entities.Event
import org.greenrobot.eventbus.Subscribe
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.greenrobot.eventbus.EventBus as Bus

@RunWith(JUnit4::class)
class EventBusTest {
    private lateinit var bus: Bus

    private val eventList = mutableListOf<Any>()

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
    }

    @Test
    fun `should return false when unregister called`() {
        EventBus.unregister(this)

        assertThat(bus.isRegistered(this)).isFalse()
    }

    @Test
    fun `should receive event when post event with subscribed`() {
        EventBus.register(this)

        EventBus.postEvent(mockEvent(EVENT_CODE_NORMAL))

        assertThat(eventList.size).isEqualTo(1)
    }

    @Test
    fun `should not receive event when post event without subscribed`() {
        EventBus.postEvent(mockEvent(EVENT_CODE_NORMAL))

        assertThat(eventList.size).isEqualTo(0)
    }

    @Test
    @Throws(InterruptedException::class)
    fun `should receive event when post sticky event with subscribed`() {
        val event = mockEvent(EVENT_CODE_STICKY)

        EventBus.postStickyEvent(event)
        EventBus.register(this)

        assertThat(eventList.size).isEqualTo(1)
    }

    @Test
    fun `should not receive event when post sticky event without subscribed`() {
        EventBus.postStickyEvent(mockEvent(EVENT_CODE_STICKY))

        assertThat(eventList.size).isEqualTo(0)
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
