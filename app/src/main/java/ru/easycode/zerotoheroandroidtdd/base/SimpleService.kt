package ru.easycode.zerotoheroandroidtdd.base

import retrofit2.http.GET
import retrofit2.http.Url
import ru.easycode.zerotoheroandroidtdd.SimpleResponse

interface SimpleService {
    @GET
    suspend fun fetch(@Url url: String) : SimpleResponse
}