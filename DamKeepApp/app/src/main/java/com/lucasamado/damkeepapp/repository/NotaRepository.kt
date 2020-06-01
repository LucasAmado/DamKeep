package com.lucasamado.damkeepapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.lucasamado.damkeepapp.common.MyApp
import com.lucasamado.damkeepapp.models.CreateNota
import com.lucasamado.damkeepapp.models.Nota
import com.lucasamado.damkeepapp.retrofit.NotasService
import com.lucasamado.damkeepapp.retrofit.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaRepository {
    var notasService: NotasService
    var serviceGenerator: ServiceGenerator = ServiceGenerator()
    var nota: MutableLiveData<Nota>
    var notasList: MutableLiveData<List<Nota>>

    init {
        notasService = serviceGenerator.getNotasService()
        nota = MutableLiveData()
        notasList = MutableLiveData()
    }


    fun getNotasUser(): MutableLiveData<List<Nota>>{
        val call: Call<List<Nota>> = notasService.getNotasUser()

        call.enqueue(object: Callback<List<Nota>>{
            override fun onResponse(call: Call<List<Nota>>, response: Response<List<Nota>>) {
                if(response.isSuccessful){
                    notasList.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al cargar las notas", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Nota>>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
        return notasList
    }

    fun crearNota(createNota: CreateNota): MutableLiveData<Nota>{
        val call: Call<Nota> = notasService.createNota(createNota)

        call.enqueue(object: Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al crear la nota", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }

    fun getNotaById(id_nota: String): MutableLiveData<Nota>{
        val call: Call<Nota> = notasService.getNota(id_nota)

        call.enqueue(object: Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al cargar los datos de la nota", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }

    fun editarNota(id_nota: String, createNota: CreateNota): MutableLiveData<Nota>{
        val call: Call<Nota> = notasService.editNota(id_nota, createNota)

        call.enqueue(object: Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al editar la nota", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }


    fun borrarNota(id_nota: String){
        val call: Call<Void> = notasService.deleteNota(id_nota)

        call.enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(MyApp.instance, "Nota borrada correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(MyApp.instance, "Error al borrar la nota", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

}