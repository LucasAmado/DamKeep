package com.lucasamado.damkeepapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("user")
    @Expose
    val user: User
)