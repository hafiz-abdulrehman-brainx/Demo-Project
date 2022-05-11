package com.example.demoproject.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder().url(originalHttpUrl)
        return chain.proceed(requestBuilder.build())
    }
}