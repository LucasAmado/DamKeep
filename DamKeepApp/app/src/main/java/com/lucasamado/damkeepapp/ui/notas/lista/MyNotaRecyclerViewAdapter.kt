package com.lucasamado.damkeepapp.ui.notas.lista

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.lucasamado.damkeepapp.R
import com.lucasamado.damkeepapp.common.Constantes
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.Nota
import com.lucasamado.damkeepapp.ui.notas.detalle.NotaDetalleActivity
import kotlinx.android.synthetic.main.fragment_nota.view.*


class MyNotaRecyclerViewAdapter() : RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder>() {

    private var notas: List<Nota> = ArrayList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val notaSelected = v.tag as Nota
            val idNota = notaSelected.id

            var intent = Intent(MyApp.instance, NotaDetalleActivity::class.java).apply {
                putExtra(Constantes.ID_NOTA, idNota.toString())
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            MyApp.instance.startActivity(intent)

            Toast.makeText(MyApp.instance, idNota.toString(), Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas[position]
        holder.mIdView.text = item.titulo

        holder.mView.tag = item

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = notas.size

    fun setData(notasList: List<Nota>) {
        notas = notasList

        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.textViewTitulo
    }
}
