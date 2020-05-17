package com.lucasamado.damkeepapp.retrofit

import com.lucasamado.damkeepapp.models.CreateNota
import com.lucasamado.damkeepapp.models.Nota
import retrofit2.Call
import retrofit2.http.*

interface NotasService {

    @GET("notas/")
    fun getNotasUser(): Call<List<Nota>>

    @GET("notas/{id}")
    fun getNota(@Path("id") id: String): Call<Nota>

    @POST("notas/")
    fun createNota(@Body createNota: CreateNota): Call<Nota>

    @PUT("notas/{id}")
    fun editNota(@Path("id") id: String, @Body createNota: CreateNota): Call<Nota>

    @DELETE("notas/{id}")
    fun deleteNota(@Path("id") id: String): Call<Void>
}