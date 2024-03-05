package ru.easycode.zerotoheroandroidtdd.data

import kotlinx.coroutines.delay
import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            delay(1000)
            return try {
                val result = service.fetch(url)
                LoadResult.Success(result)
            } catch (e: UnknownHostException) {
                LoadResult.Error(true)
            } catch (e: Exception) {
                LoadResult.Error(false)
            }
        }
    }
}