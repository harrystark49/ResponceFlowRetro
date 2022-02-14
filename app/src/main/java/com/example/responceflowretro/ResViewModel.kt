package com.example.responceflowretro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ResViewModel @Inject constructor(@Named("retro")s:retroInterface):ViewModel() {

    var repo=Repo(s)

    val responce=MutableStateFlow(
        Responce(Status.LOADING,res(),"")
    )

    val responce1=responce.stateIn(
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