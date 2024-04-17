package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals

interface FakeNavigation {

    interface Update : Navigation.Update {
        fun checkScreen(expected: Screen)
    }

    interface Mutable : Navigation.Mutable, Update


    companion object {
        const val NAVIGATE = "Navigation#navigate"
    }

    class Base(private val order: Order) : Mutable, Update {

        private lateinit var actual: Screen

        override fun update(screen: Screen) {
            actual = screen
            order.add(NAVIGATE)
        }

        override fun checkScreen(expected: Screen) {
            assertEquals(expected, actual)
        }

        override fun liveData(): LiveData<Screen> {
            TODO("Not yet implemented")
        }
    }
}