package com.example.responceflowretro

data class Responce<out T>(val status: Status,val data:T?,val msg:String?){
    companion object{
        fun <T>success(data:T?):Responce<T>{
            return Responce(Status.SUCCESS,data,null)
        }
        fun <T>error(msg: String?):Responce<T>{
            return Responce(Status.ERROR,null,msg)
        }
        fun <T>loading():Responce<T>{
            return Responce(Status.LOADING,null,null)
        }
    }
}

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}