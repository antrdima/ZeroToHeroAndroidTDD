package ru.easycode.zerotoheroandroidtdd.core

import java.time.LocalDate

interface Now {

    fun nowMillis(): Long

    class Base() : Now{
        override fun nowMillis(): Long {
            return System.currentTimeMillis()
        }
    }
}
