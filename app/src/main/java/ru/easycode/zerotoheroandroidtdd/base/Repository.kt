package ru.easycode.zerotoheroandroidtdd.base

import ru.easycode.zerotoheroandroidtdd.SimpleResponse

interface Repository {
    suspend fun load(): SimpleResponse

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): SimpleResponse {
            return service.fetch(url)
        }
    }
}