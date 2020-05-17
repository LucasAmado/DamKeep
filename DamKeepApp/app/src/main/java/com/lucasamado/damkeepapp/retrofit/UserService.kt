package com.lucasamado.damkeepapp.retrofit

import com.lucasamado.damkeepapp.models.User
import com.lucasamado.damkeepapp.models.CreateUser
import com.lucasamado.damkeepapp.models.LoginRequest
import com.lucasamado.damkeepapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("user/signup")
    fun createUser(@Body newUser: CreateUser): Call<User>

    @POST("auth/login")
    fun doLogin(@Body user: LoginRequest): Call<LoginResponse>
}