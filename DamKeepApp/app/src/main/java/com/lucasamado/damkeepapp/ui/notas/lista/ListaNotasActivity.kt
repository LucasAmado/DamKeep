package com.lucasamado.damkeepapp.ui.notas.lista

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.ui.notas.NuevaNotaDialogFragment

import kotlinx.android.synthetic.main.activity_lista_notas.*

class ListaNotasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_notas)
        setSupportActionBar(toolbar)

        addNota.setOnClickListener {
            val dialog: DialogFragment = NuevaNotaDialogFragment()
            dialog.show(supportFragmentManager, "NuevaNota")
        }
    }

}
