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
import com.lucasamado.damkeepapp.models.CreateUser
import com.lucasamado.damkeepapp.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var registerViewModel: RegisterViewModel
    lateinit var username: EditText
    lateinit var fullname: EditText
    lateinit var password: EditText
    lateinit var password2: EditText
    lateinit var button_signUp: Button
    lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.editTextUsername)
        fullname = findViewById(R.id.editTextFullname)
        password = findViewById(R.id.editTextPass)
        password2 = findViewById(R.id.editTextPass2)
        button_signUp = findViewById(R.id.buttonRegister)
        login = findViewById(R.id.textViewLogin)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        login.setOnClickListener {
            val intent = Intent(MyApp.instance, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            MyApp.instance.startActivity(intent)
        }

        button_signUp.setOnClickListener { v ->
            registerViewModel.registrarUsuario(
                CreateUser(
                    username = username.text.toString(),
                    fullname = fullname.text.toString(),
                    password = password.text.toString(),
                    password2 = password2.text.toString()
                )
            ).observe(this, Observer {
                if(it != null) {
                    val intent = Intent(MyApp.instance, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    MyApp.instance.startActivity(intent)
                }
            })
        }
    }
}
