package com.example.responceflowretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var s=ResViewModel()
        s.getdata(5)
        lifecycleScope.launchWhenCreated {
            s.responce1.collect {
                when(it.status){
                    Status.LOADING->{
                        Log.d("dataaa","loading")
                    }
                    Status.SUCCESS->{
                        Log.d("dataaa","${it.data}")
                    }
                    Status.ERROR->{
                        Log.d("dataaa","${it.msg}")

                    }
            }
            }
        }
    }
}