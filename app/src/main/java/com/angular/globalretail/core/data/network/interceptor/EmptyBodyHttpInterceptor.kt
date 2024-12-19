package com.angular.globalretail.core.data.network.interceptor

import com.angular.globalretail.core.utils.orFalse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class EmptyBodyHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()

        try {
            val response = chain.proceed(request)

            return when (response.code) {
                200, 204, 205 -> {
                    val content = response.body?.contentLength()?.or(0)

                    if (content == 0L || response.body?.source()?.exhausted().orFalse() ) {
                        val emptyBody = "{ success: \"true\" }"
                            .toResponseBody("text/plain".toMediaType()) // Convert content is SuccessData with result as param

                        response
                            .newBuilder()
                            .code(200)
                            .body(emptyBody)
                            .build()
                    } else response
                }

                else -> {
                    response
                }
            }
        }catch (e: IOException){
            // example throw invocation
            throw e
        }


    }
}