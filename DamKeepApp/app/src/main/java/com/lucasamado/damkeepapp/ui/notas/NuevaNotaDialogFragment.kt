package com.lucasamado.damkeepapp.ui.notas

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.CreateNota
import com.lucasamado.damkeepapp.viewModel.NotaViewModel

class NuevaNotaDialogFragment: DialogFragment() {

    lateinit var v: View
    lateinit var titulo: EditText
    lateinit var contenido: EditText
    lateinit var notaViewModel: NotaViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = activity?.let { AlertDialog.Builder(it) }

        notaViewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        builder?.setTitle("Nueva nota")
        builder?.setMessage("Introduza los datos de la nueva nota")

        builder?.setCancelable(true)

        v = LayoutInflater.from(activity).inflate(R.layout.nueva_nota_dialog, null)
        builder?.setView(v)

        titulo = v.findViewById(R.id.textViewTitulo)
        contenido = v.findViewById(R.id.textViewContenido)
        builder?.setPositiveButton(
            R.string.save,
            DialogInterface.OnClickListener { dialog, which ->
                notaViewModel.crearNota(
                    CreateNota(
                        titulo = titulo.text.toString(),
                        contenido = contenido.text.toString()
                    )
                ).observe(this, Observer {
                    if(it != null) {
                        dialog.dismiss()
                    }
                })
            })
        builder?.setNegativeButton(
            R.string.cancel,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })

        return builder?.create()!!
    }

}
