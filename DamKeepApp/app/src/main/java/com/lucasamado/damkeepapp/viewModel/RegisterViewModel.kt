package com.lucasamado.damkeepapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasamado.damkeepapp.models.CreateUser
import com.lucasamado.damkeepapp.models.User
import com.lucasamado.damkeepapp.repository.UserRepository

class RegisterViewModel: ViewModel() {
    private var userRepository: UserRepository = UserRepository()

    fun registrarUsuario(createUser: CreateUser): LiveData<User>{
        return userRepository.createUser(createUser)
    }
}