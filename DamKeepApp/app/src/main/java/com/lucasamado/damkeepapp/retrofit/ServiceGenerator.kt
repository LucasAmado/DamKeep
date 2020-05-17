package com.lucasamado.damkeepapp.retrofit

import com.lucasamado.damkeepapp.common.Constantes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceGenerator {
    private val notasService: NotasService
    private val userService: UserService
    private val retrofit: Retrofit

    init {
        val httpLoginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClientBuilder = OkHttpClient.Builder()

        val client = okHttpClientBuilder.addInterceptor(httpLoginInterceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        notasService = retrofit.create(NotasService::class.java)

        userService = retrofit.create(UserService::class.java)
    }

    fun getNotasService() = notasService
    fun getUserService() = userService
}