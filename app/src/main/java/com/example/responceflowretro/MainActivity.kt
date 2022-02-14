package com.example.responceflowretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel:ResViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getdata(5)
        collectLatestLifeCycleFlow(viewModel.responce1){
            when (it.status) {
                Status.LOADING -> {
                    Log.d("dataaa", "loading")
                }
                Status.SUCCESS -> {
                    Log.d("dataaa", "${it.data}")
                }
                Status.ERROR -> {
                    Log.d("dataaa", "${it.msg}")

                }
            }
        }

    }

    fun <T> ComponentActivity.collectLatestLifeCycleFlow(flow: Flow<T>, collct:suspend (T)->Unit){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                flow.collectLatest(collct)
            }
        }
    }

}