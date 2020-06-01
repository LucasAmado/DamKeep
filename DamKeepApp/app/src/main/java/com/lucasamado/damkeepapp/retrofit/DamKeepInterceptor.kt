package com.lucasamado.damkeepapp.retrofit

import com.lucasamado.damkeepapp.common.Constantes
import com.lucasamado.damkeepapp.common.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class DamKeepInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request

        val token = SharedPreferencesManager().getSharedPreferences()
            .getString(Constantes.TOKEN, "")

        val requestBuilder: Request.Builder =
            original.newBuilder().header("Authorization","Bearer " + token)

        request = requestBuilder.build()

        return chain.proceed(request)
    }

}