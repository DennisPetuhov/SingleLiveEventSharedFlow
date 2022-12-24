package com.example.singleliveeventsharedflow

import android.content.Intent
import android.opengl.Visibility
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.singleliveeventsharedflow.databinding.ActivityMain2Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    val vm by viewModels<MainViewModel2>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        binding.changeActivityButton.setOnClickListener {
            println("!!!!")
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        binding.SingleLiveEvent.setOnClickListener {
            vm.upload(binding.myText.text.toString())
        }


    }


    fun initObserver() {
        lifecycleScope.launch {


                 vm.action.collect{
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
//                binding.SingleLiveEvent.visibility=View.INVISIBLE
                                 }

        }
    }


//    fun initObserver() {
//        lifecycleScope.launch {
//            vm.action.collect {
//
//                Toast.makeText(this@MainActivity2, it, Toast.LENGTH_SHORT).show()
//                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
//
//            }
//        }
//
//
//    }
}