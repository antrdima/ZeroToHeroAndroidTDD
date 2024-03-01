package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.delay

interface Repository {
    suspend fun load()

    class Base : Repository {
        override suspend fun load() {
            Thread.sleep(1000)
            delay(1000)
        }
    }
}