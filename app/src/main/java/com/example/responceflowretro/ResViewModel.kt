package com.example.responceflowretro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResViewModel:ViewModel() {

    var repo=Repo(AppConfig.ApiService())

    var responce=MutableStateFlow(
        Responce(Status.LOADING,res(),"")
    )

    init {
        getdata(1)
    }

     fun getdata(i: Int) {
         viewModelScope.launch {
             responce.value=Responce.loading()
             repo.getData(i)
                 .catch {
                     responce.value= Responce.error(it.message.toString())
                 }
                 .collect {da->
                     responce.value=Responce.success(da.data)
                 }
         }

    }
}