package com.lucasamado.damkeepapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasamado.damkeepapp.models.Nota
import com.lucasamado.damkeepapp.repository.NotaRepository

class NotaDetalleViewModel: ViewModel() {

    private var notaRepository: NotaRepository = NotaRepository()

    fun getNotaById(id: String): LiveData<Nota>{
        return notaRepository.getNotaById(id)
    }

    fun borrarNota(id: String){
        return notaRepository.borrarNota(id)
    }
}