package com.lucasamado.damkeepapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasamado.damkeepapp.models.CreateNota
import com.lucasamado.damkeepapp.models.Nota
import com.lucasamado.damkeepapp.repository.NotaRepository

class NotaViewModel: ViewModel() {

    private var notaRepository: NotaRepository = NotaRepository()

    fun crearNota(createNota: CreateNota): LiveData<Nota> {
        return notaRepository.crearNota(createNota)
    }

    fun editarNota(id: String, createNota: CreateNota): LiveData<Nota>{
        return notaRepository.editarNota(id, createNota)
    }

    fun getNotas(): LiveData<List<Nota>> {
        return notaRepository.getNotasUser()
    }
}