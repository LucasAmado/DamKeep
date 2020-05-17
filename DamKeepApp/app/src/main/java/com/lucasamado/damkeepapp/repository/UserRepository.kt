package com.lucasamado.damkeepapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.CreateUser
import com.lucasamado.damkeepapp.models.LoginRequest
import com.lucasamado.damkeepapp.models.LoginResponse
import com.lucasamado.damkeepapp.models.User
import com.lucasamado.damkeepapp.retrofit.ServiceGenerator
import com.lucasamado.damkeepapp.retrofit.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var userService: UserService
    var serviceGenerator: ServiceGenerator = ServiceGenerator()
    var user: MutableLiveData<User>

    init {
        userService = serviceGenerator.getUserService()
        user = MutableLiveData()
    }

    fun createUser(createUser: CreateUser): MutableLiveData<User>{
        val call: Call<User> = userService.createUser(createUser)
        call.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    user.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al crear un nuevo usuario", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_LONG).show()
            }
        })
        return user
    }


    fun doLogin(loginRequest: LoginRequest): MutableLiveData<User> {
        val call: Call<LoginResponse> = userService.doLogin(loginRequest)

        call?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    user.value = response.body()?.user
                }else{
                    Toast.makeText(MyApp.instance, "Error al hacer el login", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_LONG).show()
            }
        })

        return user
    }
}