package com.lucasamado.damkeepapp.ui.notas.detalle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.common.Constantes
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.CreateNota
import com.lucasamado.damkeepapp.ui.notas.lista.ListaNotasActivity
import com.lucasamado.damkeepapp.viewModel.NotaDetalleViewModel
import com.lucasamado.damkeepapp.viewModel.NotaViewModel

class EditarNotaActivity : AppCompatActivity() {

    lateinit var notaViewModel: NotaViewModel
    lateinit var notaDetalleViewModel: NotaDetalleViewModel
    lateinit var id_nota: String
    lateinit var titulo: EditText
    lateinit var contenido: EditText
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)

        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
        notaDetalleViewModel = ViewModelProvider(this).get(NotaDetalleViewModel::class.java)

        guardar = findViewById(R.id.buttonSave)
        titulo = findViewById(R.id.editTextTitulo)
        contenido = findViewById(R.id.editTextContenido)

        id_nota = intent.getStringExtra(Constantes.ID_NOTA)

        loadNota()

        guardar.setOnClickListener(View.OnClickListener {
            notaViewModel.editarNota(id_nota, CreateNota(titulo.text.toString(), contenido.text.toString())).observe(this, Observer {
                val intent = Intent(MyApp.instance, ListaNotasActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            })
        })

    }

    private fun loadNota(){
        notaDetalleViewModel.getNotaById(id_nota).observe(this, Observer {
            titulo.setText(it.titulo)
            contenido.setText(it.contenido)
        })
    }
}
