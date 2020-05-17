package com.lucasamado.damkeepapp.ui.notas.detalle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.common.Constantes
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.ui.notas.lista.ListaNotasActivity
import com.lucasamado.damkeepapp.viewModel.NotaDetalleViewModel

class NotaDetalleActivity : AppCompatActivity() {

    private lateinit var id_nota: String
    private lateinit var notaDetalleViewModel: NotaDetalleViewModel
    lateinit var titulo: TextView
    lateinit var fecha_creacion: TextView
    lateinit var fecha_edicion: TextView
    lateinit var contenido: TextView
    lateinit var edit: Button
    lateinit var delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_detalle)


        id_nota = intent.getStringExtra(Constantes.ID_NOTA)

        notaDetalleViewModel = ViewModelProvider(this).get(NotaDetalleViewModel::class.java)

        titulo = findViewById(R.id.textViewTitulo)
        fecha_creacion = findViewById(R.id.textViewCreacion)
        fecha_edicion = findViewById(R.id.textViewEdicion)
        contenido = findViewById(R.id.textViewContenido)
        delete = findViewById(R.id.floatingActionButtonDelete)
        edit = findViewById(R.id.floatingActionButtonEdit)

        loadNota()

        edit.setOnClickListener {
            val intent = Intent(MyApp.instance, EditarNotaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constantes.ID_NOTA, id_nota)
            }
            startActivity(intent)
        }

        delete.setOnClickListener {
            notaDetalleViewModel.borrarNota(id_nota)
            val intent = Intent(MyApp.instance, ListaNotasActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }
    }

    private fun loadNota(){
        notaDetalleViewModel.getNotaById(id_nota).observe(this, Observer {
            titulo.text = it.titulo
            fecha_creacion.text = it.fecha_creacion
            fecha_edicion.text = it.fecha_edicion
            contenido.text = it.contenido
        })
    }
}
