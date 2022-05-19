package com.example.responceflowretro

import okhttp3.Interceptor
import okhttp3.Response

class OaAuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req=chain.request().newBuilder()
        req.apply {
            addHeader("Content-Type", "application/json")
            addHeader("X-Requested-With", "XMLHttpRequest")
        }

        return chain.proceed(req.build())
    }
}