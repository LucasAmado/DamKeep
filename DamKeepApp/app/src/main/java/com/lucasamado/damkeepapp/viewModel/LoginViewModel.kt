package com.lucasamado.damkeepapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasamado.damkeepapp.models.LoginRequest
import com.lucasamado.damkeepapp.models.User
import com.lucasamado.damkeepapp.repository.UserRepository

class LoginViewModel: ViewModel() {
    private var userRepository: UserRepository = UserRepository()

    fun doLogin(loginRequest: LoginRequest): LiveData<User>{
        return userRepository.doLogin(loginRequest)
    }
}