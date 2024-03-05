package ru.easycode.zerotoheroandroidtdd.data

import retrofit2.http.GET
import retrofit2.http.Url

interface SimpleService {
    @GET
    suspend fun fetch(@Url url: String) : SimpleResponse
}