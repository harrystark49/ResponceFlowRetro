package com.example.responceflowretro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ResViewModel:ViewModel() {

    var repo=Repo(AppConfig.ApiService())

    val responce=MutableStateFlow(
        Responce(Status.LOADING,res(),"")
    )

    val responce1=responce.asStateFlow().stateIn(
        initialValue = Responce(Status.LOADING,res(),""),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(4000)
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