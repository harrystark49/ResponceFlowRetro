package com.example.responceflowretro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow

class Repo(var retroInterface: retroInterface) {
    suspend fun getData(id:Int):kotlinx.coroutines.flow.Flow<Responce<res>>{
        return flow {
            var result=retroInterface.getData(id)
            emit(Responce.success(result))
        }.flowOn(Dispatchers.IO)
    }
}