package com.lucasamado.damkeepapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.LoginRequest
import com.lucasamado.damkeepapp.ui.notas.lista.ListaNotasActivity
import com.lucasamado.damkeepapp.viewModel.LoginViewModel

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var register: TextView
    lateinit var button_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        username = findViewById(R.id.editTextUsername)
        password = findViewById(R.id.editTextPass)
        register = findViewById(R.id.textViewSignUp)
        button_login = findViewById(R.id.buttonLogin)

        register.setOnClickListener {
            val intent = Intent(MyApp.instance, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            MyApp.instance.startActivity(intent)
        }

        button_login.setOnClickListener { v ->
            loginViewModel.doLogin(
                LoginRequest(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
            ).observe(this, Observer {
                if (it != null) {
                    val intent = Intent(MyApp.instance, ListaNotasActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    MyApp.instance.startActivity(intent)
                }
            })
        }
    }
}
