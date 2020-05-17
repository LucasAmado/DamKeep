package com.lucasamado.damkeepapp.models

data class CreateUser(
    val username: String,
    val fullname: String,
    val password: String,
    val password2: String
){

}